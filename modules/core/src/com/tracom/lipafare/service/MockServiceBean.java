package com.tracom.lipafare.service;

import com.tracom.lipafare.models.CustomerMock;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service(MockService.NAME)
public class MockServiceBean implements MockService {
    final List<CustomerMock> smallDb = Arrays.asList(
            new CustomerMock(
                    "254722111222",
                    "Betty Kirii",
                    "255475",
                    "CodeOwner",
                    "1122",
                    "sw"
            ),
            new CustomerMock(
                    "254722111223",
                    "Kech Kibet",
                    "5456421",
                    "CodeOwner",
                    "1122",
                    "en"
            ),
            new CustomerMock(
                    "254722111224",
                    "Bill Kariri",
                    "56464",
                    "CodeManager",
                    "1122",
                    "sw"
            ),
            new CustomerMock(
                    "254722111225",
                    "Amos Wachira",
                    "456546",
                    "CodeManager",
                    "1122",
                    "en"
            )
    );
    @Inject
    private MockService mockService;

    @Override
    public CustomerMock getCustomerForPhoneNumber(String phoneNumber) {
        Optional<CustomerMock> first = smallDb.stream().filter(customerMock -> {
            return customerMock.getPhoneNumber().equals(phoneNumber);
        }).findFirst();

        return first.orElse(new CustomerMock(
                phoneNumber,
                "",
                "",
                "Passenger",
                "1122",
                "en"
        ));


    }
}