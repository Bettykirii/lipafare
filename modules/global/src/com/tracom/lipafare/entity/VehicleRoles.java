package com.tracom.lipafare.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum VehicleRoles implements EnumClass<String> {

    SMS("1"),
    WITHDRAW("2"),
    WITHDRAWANDSMS("3");

    private String id;

    VehicleRoles(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static VehicleRoles fromId(String id) {
        for (VehicleRoles at : VehicleRoles.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}