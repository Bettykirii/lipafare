package com.tracom.lipafare.service;

import com.tracom.lipafare.models.ResponseWrapper;


public interface UssdService {
    String NAME = "lipafare_UssdService";


    ResponseWrapper getRegistrationStatus(String phoneNumber);//after call

    ResponseWrapper registerUser(String phoneNumber, String firstName, String otherNames, String idNumber, String locale);//SaveSaccoMembers - perfect
    ResponseWrapper loginUser(String phoneNumber, String password);//We use our backend

    ResponseWrapper balanceEnquiry(String phoneNumber, String associationCode);
    ResponseWrapper changePin(String phoneNumber, String newPin);

    ResponseWrapper changeLanguage(String phoneNumber, String newLocale);



}