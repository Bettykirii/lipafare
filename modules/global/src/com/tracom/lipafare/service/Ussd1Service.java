package com.tracom.lipafare.service;

import com.tracom.lipafare.models.ResponseWrapper;


public interface Ussd1Service {
    String NAME = "lipafare_Ussd1Service";


    ResponseWrapper getRegistrationStatus(String phoneNumber);//after call

    ResponseWrapper registerUser(String phoneNumber, String firstName, String otherNames, String idNumber, String locale ,String customerType ,String pin ,String salesAgentCode);//SaveCustomers - perfect
    ResponseWrapper loginUser(String phoneNumber, String password);//We use our backend

    ResponseWrapper balanceEnquiry(String phoneNumber, String associationCode);
    ResponseWrapper changePin(String phoneNumber, String newPin);

    ResponseWrapper changeLanguage(String phoneNumber, String newLocale);



}