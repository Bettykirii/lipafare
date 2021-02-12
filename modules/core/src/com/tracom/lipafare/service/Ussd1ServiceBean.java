package com.tracom.lipafare.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.tracom.lipafare.entity.CustomerType;
import com.tracom.lipafare.entity.RegisterUsers;
import com.tracom.lipafare.models.CustomerMock;
import com.tracom.lipafare.models.ResponseWrapper;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    final List<RegisterUsers> users = (List<RegisterUsers>) dataManager.load(RegisterUsers.class)
            .query("select e from lipafare_RegisterUsers e where e.phoneNumber = :phoneNumber", RegisterUsers.class)
            .parameter("phoneNumber", phoneNumber).list();
        if (users.size() > 0)
                wrapper.setData(users);
        else {
        RegisterUsers registerUsers = metadata.create(RegisterUsers.class);
        registerUsers.setLocale("en");
        registerUsers.setCustomerType(CustomerType.CODEOWNER);
        registerUsers.setCustomerType(CustomerType.CODEMANAGER);
        registerUsers.setCustomerType(CustomerType.PASSENGER);

        dataManager.commit(registerUsers);

    }
        return wrapper;
}


    @Override
    public ResponseWrapper registerUser(String phoneNumber, String firstName, String otherNames, String idNumber, String locale ,String customerType , String pin) {
        ResponseWrapper<Object> wrapper = new ResponseWrapper<>();
        wrapper.setMessage("Registered successfully");

        final RegisterUsers users = metadata.create(RegisterUsers.class);
        users.setPhoneNumber(phoneNumber);
        users.setFirstName(firstName);
        users.setOtherNames(otherNames);
        users.setIdNumber(idNumber);
        users.setLocale(locale);
        users.setCustomerType(CustomerType.fromId(customerType));
        users.setPin(pin);



        dataManager.commit(users);

        return wrapper;
    }

    @Override
    public ResponseWrapper loginUser(String phoneNumber, String password) {

        ResponseWrapper<Object> wrapper = new ResponseWrapper<>();
        wrapper.setMessage("Login success");

        CustomerMock customerForPhoneNumber = mockService.getCustomerForPhoneNumber(phoneNumber);
        final List<RegisterUsers> users = ge(phoneNumber);
        if (users.size()>0) {
            final RegisterUsers user = users.get(0);
            if (user.getPin().equalsIgnoreCase(password)) {

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
        return wrapper;
    }

    @Override
    public ResponseWrapper changeLanguage(String phoneNumber, String newLocale) {

        ResponseWrapper<Object> wrapper = new ResponseWrapper<>();
        return wrapper;
    }
}