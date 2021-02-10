package com.tracom.lipafare.service;

import com.tracom.lipafare.models.CustomerMock;
import com.tracom.lipafare.models.ResponseWrapper;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service(UssdService.NAME)
public class UssdServiceBean implements UssdService {
    @Inject
    private MockService mockService;

    @Override
    public ResponseWrapper getRegistrationStatus(String phoneNumber) {
        ResponseWrapper<Object> wrapper = new ResponseWrapper<>();
        wrapper.setData(mockService.getCustomerForPhoneNumber(phoneNumber));
        return wrapper;
    }

    @Override
    public ResponseWrapper registerUser(String phoneNumber, String firstName, String otherNames, String idNumber, String locale) {
        ResponseWrapper<Object> wrapper = new ResponseWrapper<>();

        return wrapper;
    }

    @Override
    public ResponseWrapper loginUser(String phoneNumber, String password) {

        ResponseWrapper<Object> wrapper = new ResponseWrapper<>();

        CustomerMock customerForPhoneNumber = mockService.getCustomerForPhoneNumber(phoneNumber);
        if (!customerForPhoneNumber.getPin().equals(password)){
            wrapper.setCode(401);
            wrapper.setMessage("Invalid PIN");
        }
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