package com.tracom.lipafare.service;

import com.tracom.lipafare.models.CustomerMock;

public interface MockService {
    String NAME = "lipafare_MockService";
    CustomerMock getCustomerForPhoneNumber(String phoneNumber);
}