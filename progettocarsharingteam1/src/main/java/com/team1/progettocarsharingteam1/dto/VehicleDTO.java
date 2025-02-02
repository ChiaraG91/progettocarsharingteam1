package com.team1.progettocarsharingteam1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team1.progettocarsharingteam1.entities.Rent;
import com.team1.progettocarsharingteam1.entities.enums.CityEnum;
import com.team1.progettocarsharingteam1.entities.enums.TypeVehicleEnum;

import java.util.List;

public class VehicleDTO {
    private Long id;
    private String brand;
    private String model;
    private String details;

    private CityEnum cityEnum;
    private boolean isAvailable;
    private TypeVehicleEnum typeVehicle;

    public VehicleDTO() {
    }

    public VehicleDTO(Long id, String brand, String model, String details, CityEnum cityEnum, boolean isAvailable, TypeVehicleEnum typeVehicle) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.details = details;
        this.cityEnum = cityEnum;
        this.isAvailable = isAvailable;
        this.typeVehicle = typeVehicle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public TypeVehicleEnum getTypeVehicle() {
        return typeVehicle;
    }

    public void setTypeVehicle(TypeVehicleEnum typeVehicle) {
        this.typeVehicle = typeVehicle;
    }


    public CityEnum getCityEnum() {
        return cityEnum;
    }

    public void setCityEnum(CityEnum cityEnum) {
        this.cityEnum = cityEnum;
    }
}
