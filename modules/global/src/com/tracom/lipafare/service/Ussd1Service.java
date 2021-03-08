package com.tracom.lipafare.service;

import com.tracom.lipafare.entity.Vehicles;
import com.tracom.lipafare.models.ResponseWrapper;

import java.util.List;


public interface Ussd1Service {
    String NAME = "lipafare_Ussd1Service";


    ResponseWrapper getRegistrationStatus(String phoneNumber);//after call

    ResponseWrapper registerUser(String phoneNumber, String firstName, String otherNames, String idNumber, String locale , String customerType , String pin , String salesAgentCode ,String customerRoles, String plateNumbers );//SaveCustomers - perfect
    ResponseWrapper loginUser(String phoneNumber, String password);//We use our backend

    ResponseWrapper balanceEnquiry(String phoneNumber);
    ResponseWrapper changePin(String phoneNumber, String newPin);
    ResponseWrapper withdraw(String phoneNumber ,String amount);

    ResponseWrapper changeLanguage(String phoneNumber, String newLocale);



}