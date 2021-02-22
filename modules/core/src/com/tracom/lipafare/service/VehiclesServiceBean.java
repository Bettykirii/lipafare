package com.tracom.lipafare.service;

import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;

import com.tracom.lipafare.entity.CustomerType;
import com.tracom.lipafare.entity.Customers;
import com.tracom.lipafare.entity.Vehicles;
import com.tracom.lipafare.models.ResponseWrapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(VehiclesService.NAME)
public class VehiclesServiceBean implements VehiclesService {

    @Inject
    private Metadata metadata;
    @Inject
    private DataManager dataManager;
    @Inject
    private UniqueNumbersService uniqueNumbersService;

    @Override
    public ResponseWrapper getVehicles(String phoneNumber) {
        final ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        final List<Vehicles> vehicles = dataManager.load(Vehicles.class).list();

        responseWrapper.setData(vehicles);

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


    private List<Customers> getCustomerByPhoneNumber(String phoneNumber) {
        final List<Customers> users = dataManager.load(Customers.class)
                .query("select e from lipafare_Customers e where e.phoneNumber=:phone")
                .parameter("phone", phoneNumber)
                .list();
        return users;
    }
}