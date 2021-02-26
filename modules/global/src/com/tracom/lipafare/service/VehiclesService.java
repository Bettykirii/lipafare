package com.tracom.lipafare.service;

import com.tracom.lipafare.models.ResponseWrapper;

public interface VehiclesService {
    String NAME = "lipafare_VehiclesService";

    ResponseWrapper getVehicles(String phoneNumber);

    ResponseWrapper registerVehicle(String phoneNumber, String plateNumber);

    ResponseWrapper removeVehicle(String phoneNumber ,String plateNumber);

    ResponseWrapper transferCode(String phoneNumber ,String vehicleCode);

    ResponseWrapper revokeCode(String phoneNumber);

    ResponseWrapper lipaFare(String vehicleCode ,String amount);
}