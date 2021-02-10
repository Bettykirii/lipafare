package com.tracom.lipafare.models;



import java.io.Serializable;

public class CustomerMock implements Serializable {
    private String phoneNumber;
    private String name;
    private String idNumber;
    private String customerType;
    private String pin;
    private String language;

    public CustomerMock(String phoneNumber, String name, String idNumber, String customerType, String pin, String language) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.idNumber = idNumber;
        this.customerType = customerType;
        this.pin = pin;
        this.language = language;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
}
