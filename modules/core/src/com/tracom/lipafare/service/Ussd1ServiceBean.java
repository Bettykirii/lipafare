package com.tracom.lipafare.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.tracom.lipafare.entity.CustomerType;
import com.tracom.lipafare.entity.Customers;
import com.tracom.lipafare.models.CustomerMock;
import com.tracom.lipafare.models.ResponseWrapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Random;

@Service(Ussd1Service.NAME)
public class Ussd1ServiceBean implements Ussd1Service {
    @Inject
    private MockService mockService;
    @Inject
    private DataManager dataManager;
    @Inject
    private Metadata metadata;

    @Override
    public ResponseWrapper getRegistrationStatus(String phoneNumber) {
        ResponseWrapper<Object> wrapper = new ResponseWrapper<>();

        final List<Customers> users = getCustomerByPhoneNumber(phoneNumber);
        if (users.size() > 0) {
            wrapper.setData(users);
        }
        else {
        Customers passengerUser = metadata.create(Customers.class);
        passengerUser.setLocale("en");
        passengerUser.setCustomerType(CustomerType.PASSENGER);

        wrapper.setData(passengerUser);

    }
        return wrapper;
}

    private List<Customers> getCustomerByPhoneNumber(String phoneNumber) {
        final List<Customers> users = dataManager.load(Customers.class)
                .query("select e from lipafare_Customers e where e.phoneNumber=:phone")
                .parameter("phone", phoneNumber)
                .list();
        return users;
    }


    @Override
    public ResponseWrapper registerUser(String phoneNumber, String firstName, String otherNames, String idNumber, String locale ,String customerType,String pin,String salesAgentCode) {
        ResponseWrapper<Object> wrapper = new ResponseWrapper<>();
        wrapper.setMessage("Registered successfully");

        final Customers customers = metadata.create(Customers.class);
        customers.setPhoneNumber(phoneNumber);
        customers.setFirstName(firstName);
        customers.setOtherNames(otherNames);
        customers.setIdNumber(idNumber);
        customers.setLocale(locale);
        customers.setCustomerType(CustomerType.fromId(customerType));
        customers.setPin(pin);
        customers.setSalesAgentCode(salesAgentCode);



        dataManager.commit(customers);

        wrapper.setData(customers);

        return wrapper;
    }

    @Override
    public ResponseWrapper loginUser(String phoneNumber, String password) {

        ResponseWrapper<Object> wrapper = new ResponseWrapper<>();
        wrapper.setMessage("Login success");

        final List<Customers> customers = getCustomerByPhoneNumber(phoneNumber);
        if (customers.size()>0) {
            final Customers customer = customers.get(0);
            if (customer.getPin().equalsIgnoreCase(password)) {
                wrapper.setMessage("Login success");
                return wrapper;
            }
        }
        wrapper.setCode(401);
        wrapper.setMessage("Invalid PIN");
        return wrapper;
    }



    @Override
    public ResponseWrapper balanceEnquiry(String phoneNumber, String associationCode) {

        ResponseWrapper<Object> wrapper = new ResponseWrapper<>();

        wrapper.setData("KES.3000");
        return wrapper;
    }

    @Override
    public ResponseWrapper changePin(String phoneNumber, String newPin) {

        ResponseWrapper<Object> wrapper = new ResponseWrapper<>();
        if (newPin.length()!=4){
            wrapper.setCode(400);
            wrapper.setMessage("Invalid PIN length");
            return wrapper;
        }
        final Customers customers = getCustomerByPhoneNumber(phoneNumber).get(0);
        customers.setPin(newPin);
        dataManager.commit(customers);
        wrapper.setMessage("PIN Change success");
        wrapper.setData(newPin);
        return wrapper;
    }


    @Override
    public ResponseWrapper changeLanguage(String phoneNumber, String newLocale) {
        final ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        final List<Customers> customers = getCustomerByPhoneNumber(phoneNumber);
        if (customers.size() == 0){
            responseWrapper.setCode(404);
            responseWrapper.setMessage("Member not found");
            return responseWrapper;
        }
        final Customers customer = customers.get(0);

        customer.setLocale(newLocale);

        dataManager.commit(customer);

        return responseWrapper;

    }
}