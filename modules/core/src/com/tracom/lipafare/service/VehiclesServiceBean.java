package com.tracom.lipafare.service;

import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;

import com.tracom.lipafare.entity.CustomerType;
import com.tracom.lipafare.entity.Customers;
import com.tracom.lipafare.entity.Vehicles;
import com.tracom.lipafare.models.ResponseWrapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service(VehiclesService.NAME)
public class VehiclesServiceBean implements VehiclesService {

    @Inject
    private Metadata metadata;
    @Inject
    private DataManager dataManager;
    @Inject
    private UniqueNumbersService uniqueNumbersService;
    @Inject
    private Logger log;


    @Override
    public ResponseWrapper getVehicles(String phoneNumber) {
        final ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        //fetch the customer by phoneNumber

        //make sure the customer actually owns the vehicle
        final List<Customers> customers = getCustomerByPhoneNumber(phoneNumber);
        final Customers customer = customers.get(0);
        if (customer.getCustomerType() != CustomerType.CODEOWNER) {
            responseWrapper.setCode(400);
            responseWrapper.setMessage("Code Owner not found");
            return responseWrapper;
        }


        final List<Vehicles> list = dataManager.load(Vehicles.class).query("select e  from lipafare_Vehicles e where e.vehicleOwner=:vehicle")
                .parameter("vehicle", customers.get(0))
                .list();

        responseWrapper.setData(list);
        return responseWrapper;
    }

    @Override
    public ResponseWrapper registerVehicle(String phoneNumber, String plateNumber) {
        ResponseWrapper<Object> wrapper = new ResponseWrapper<>();
        wrapper.setMessage("Vehicle Registered successfully");

        final Vehicles vehicles = metadata.create(Vehicles.class);
        vehicles.setVehicleCode(String.valueOf(uniqueNumbersService.getNextNumber("VehicleCode")));
        List<Customers> customers = getCustomerByPhoneNumber(phoneNumber);
        final Customers customer = customers.get(0);
        if(customer.getCustomerType()!= CustomerType.CODEOWNER){
            wrapper.setCode(400);
            wrapper.setMessage("Customer not found");
            return wrapper;
        }
        vehicles.setVehicleOwner(customer);
        vehicles.setPlateNumber(plateNumber);

        dataManager.commit(vehicles);

        wrapper.setData(vehicles);

        return wrapper;
    }

    @Override
    public ResponseWrapper removeVehicle(String phoneNumber ,String plateNumber) {
        final ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();

        //fetch vehicle by plate number
        final Vehicles vehicles1 = dataManager.load(Vehicles.class)
                .query("select e from lipafare_Vehicles  e where e.plateNumber = :plate")
                .view(  "vehicles-view")
                .parameter("plate", plateNumber)
                .one();
        //find customer by phone
        final Customers customer = getCustomerByPhoneNumber(phoneNumber).get(0);

        //make sure he customer actually owns the vehicle he is removing
        if (!vehicles1.getVehicleOwner().getId().equals(customer.getId())){
            //does not own vehicle, abort
            responseWrapper.setCode(400);
            responseWrapper.setMessage("Unknown vehicle Owner");
            return responseWrapper;
        }

        dataManager.remove(vehicles1);
        responseWrapper.setData("Car removed successfully");

        return responseWrapper;

    }

    @Override
    public ResponseWrapper transferRoles(String phoneNumber,String vehicleCode)
    {
        final ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();

        //confirm the person transferring roles is the vehicleOwner
        final List<Customers> customers = getCustomerByPhoneNumber(phoneNumber);
        if (customers.size() == 0){
            responseWrapper.setCode(404);
            responseWrapper.setMessage("Member not found");
            return responseWrapper;
        }
       //set the new vehicleOwner by transferring the vehicle code
        final Customers customer = customers.get(0);

        customer.setPhoneNumber(vehicleCode);

        dataManager.commit(customer);

        return responseWrapper;
    }

    @Override
    public ResponseWrapper revokeCode(String phoneNumber, String vehicleCode ,String role) {
        final ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();

        //check for the person revoking code
        final List<Customers> customers = getCustomerByPhoneNumber(phoneNumber);
        if (customers.size() == 0){
            responseWrapper.setCode(404);
            responseWrapper.setMessage("Member not found");
            return responseWrapper;
        }



        //the vehicle code being revoked and it's association to the roles of the User



        responseWrapper.setData("vehicle code removed successfully");

        return responseWrapper;

    }

    @Override
    public ResponseWrapper lipaFare(String vehicleCode, String amount) {
        final ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        final List<Vehicles> vehiclecode = getVehicleByCode(vehicleCode);
        if (vehiclecode.size() == 0){
            responseWrapper.setCode(404);
            responseWrapper.setMessage("Vehicle code not found");
            return responseWrapper;
        }





       responseWrapper.setMessage("Payment successful, you will receive a confirmation message from mpesa ");
        responseWrapper.setData("");
        return responseWrapper;
    }




    @Override
    public ResponseWrapper verifyPayment(String vehicleCode) {
        final ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();








        return null;
    }
    private List<Vehicles> getVehicleByCode(String vehicleCode) {
        return dataManager.load(Vehicles.class)
                .query("select e from lipafare_Vehicles e where e.vehicleCode = :code")
                .parameter("code", vehicleCode)
                .list();
    }

    private List<Customers> getCustomerByPhoneNumber(String phoneNumber) {
        final List<Customers> users = dataManager.load(Customers.class)
                .query("select e from lipafare_Customers e where e.phoneNumber=:phone")
                .parameter("phone", phoneNumber)
                .list();
        return users;
    }
}