package com.tracom.lipafare.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Table(name = "LIPAFARE_VEHICLES")
@Entity(name = "lipafare_Vehicles")
public class Vehicles extends StandardEntity {
    private static final long serialVersionUID = 6988361464386366088L;

    @Column(name = "PLATE_NUMBER", nullable = false, unique = true)
    @NotNull
    private String plateNumber;

    @Column(name = "VEHICLE_CODE", nullable = false, unique = true)
    @NotNull
    private String vehicleCode;

    @JoinColumn(name = "VEHICLE_OWNER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customers vehicleOwner;




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public UUID getId() {
        return id;
    }



    public void setVehicleOwner(Customers vehicleOwner) {
        this.vehicleOwner = vehicleOwner;
    }

    public Customers getVehicleOwner() {
        return vehicleOwner;
    }


    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }



    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }


}