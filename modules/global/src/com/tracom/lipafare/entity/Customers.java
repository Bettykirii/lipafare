package com.tracom.lipafare.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "LIPAFARE_CUSTOMERS")
@Entity(name = "lipafare_Customers")
@NamePattern("%s|firstName")
public class Customers extends StandardEntity {
    private static final long serialVersionUID = 8938575171497915601L;


    @Column(name = "CUSTOMER_TYPE")
    private String customerType;

    @Column(name = "CUSTOMER_ROLES")
    private String customerRoles;

    @OneToMany(mappedBy = "vehicleOwner")
    private List<Vehicles> plateNumber;

    @Column(name = "SALES_AGENT_CODE")
    private String salesAgentCode;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "PIN")
    private String pin;

    @NotNull
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "OTHER_NAMES", nullable = false)
    private String otherNames;

    @NotNull
    @Column(name = "ID_NUMBER", nullable = false)
    private String idNumber;

    @Column(name = "LOCALE")
    private String locale;

    @Transient
    private String plateNumbers;

    public String getPlateNumbers() {
        return plateNumbers;
    }

    public void setPlateNumbers(String plateNumbers) {
        this.plateNumbers = plateNumbers;
    }

    public List<Vehicles> getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(List<Vehicles> plateNumber) {
        this.plateNumber = plateNumber;
    }

    public VehicleRoles getCustomerRoles() {
        return customerRoles == null ? null : VehicleRoles.fromId(customerRoles);
    }

    public void setCustomerRoles(VehicleRoles customerRoles) {
        this.customerRoles = customerRoles == null ? null : customerRoles.getId();
    }

    public String getSalesAgentCode() {
        return salesAgentCode;
    }

    public void setSalesAgentCode(String salesAgentCode) {
        this.salesAgentCode = salesAgentCode;
    }


    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType == null ? null : customerType.getId();
    }

    public CustomerType getCustomerType() {
        return customerType == null ? null : CustomerType.fromId(customerType);
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
