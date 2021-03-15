package com.tracom.lipafare.service;

import com.tracom.lipafare.entity.Customers;
import com.tracom.lipafare.entity.VehiclePermissionRegister;
import com.tracom.lipafare.entity.VehicleRoles;
import com.tracom.lipafare.entity.Vehicles;

public interface PermissionService {
    String NAME = "lipafare_PermissionService";

    void registerInitialPermissions(Customers customer, Vehicles vehicles);
    void transferPermissions(Customers fromCustomer, Customers toCustomer, VehicleRoles roles, Vehicles vehicles);
    void revokePermission(Customers fromCustomer, Customers toCustomer, VehicleRoles roles, Vehicles vehicles);

}