package com.tracom.lipafare.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@Table(name = "LIPAFARE_VEHICLE_PERMISSION_REGISTER")
@Entity(name = "lipafare_VehiclePermissionRegister")
public class VehiclePermissionRegister extends StandardEntity {
    private static final long serialVersionUID = -5085699822249283535L;

    @JoinColumn(name = "CUSTOMER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customers customer;

    @Column(name = "PERMISSION")
    private String permission;

    public VehicleRoles getPermission() {
        return permission == null ? null : VehicleRoles.fromId(permission);
    }

    public void setPermission(VehicleRoles permission) {
        this.permission = permission == null ? null : permission.getId();
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public Customers getCustomer() {
        return customer;
    }

}