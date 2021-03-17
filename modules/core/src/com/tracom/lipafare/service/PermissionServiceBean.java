package com.tracom.lipafare.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.tracom.lipafare.entity.*;
import com.tracom.lipafare.models.ResponseWrapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

import java.util.List;

@Service(PermissionService.NAME)
public class PermissionServiceBean implements PermissionService {

    @Inject
    private Metadata metadata;
    @Inject
    private DataManager dataManager;

    @Override
    public void registerInitialPermissions(Customers customer, Vehicles vehicles) {
        ResponseWrapper<Object> wrapper = new ResponseWrapper<>();



        //add all 2 permissions for this user on the vehicle
        //SMS
        final VehiclePermissionRegister smsPermissions = metadata.create(VehiclePermissionRegister.class);
        smsPermissions.setCustomer(customer);
        smsPermissions.setPermission(VehicleRoles.SMS);
        dataManager.commit(smsPermissions);

        //WITHDRAW
        final VehiclePermissionRegister withdrawPermissions = metadata.create(VehiclePermissionRegister.class);
        withdrawPermissions.setCustomer(customer);
        withdrawPermissions.setPermission(VehicleRoles.WITHDRAW);
        dataManager.commit(withdrawPermissions);


    }


    @Override
    public void transferPermissions(Customers fromCustomer, Customers toCustomer, VehicleRoles roles, Vehicles vehicles) {
        dataManager.load(Customers.class)
        .id(fromCustomer.getId())
        .view("customers-view-minimal");


        //search permission in the permission table
        //get the permissions
        //if permission doesn't exist tho the error





        //transfer that the new guy




        //delete the initial permission


        final VehiclePermissionRegister transfer = metadata.create(VehiclePermissionRegister.class);
        //the customer from whom permissions are from to the next customer
        transfer.setCustomer(fromCustomer);
        transfer.setCustomer(toCustomer);

        //the roles and vehicles being set to the customer

    }

    @Override
    public void revokePermission(Customers fromCustomer, Customers toCustomer, VehicleRoles roles, Vehicles vehicles) {

    }
}