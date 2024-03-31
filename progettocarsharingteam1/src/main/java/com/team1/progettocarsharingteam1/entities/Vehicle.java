package com.team1.progettocarsharingteam1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team1.progettocarsharingteam1.entities.enums.TypeVehicleEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String details;

    @Column(nullable = false)
    private boolean isAvailable;

    @Enumerated
    private TypeVehicleEnum typeVehicle;
    @OneToMany(mappedBy = "vehicle")
    @JsonIgnore
    private List<Rent> rentals;

    public Vehicle(long id, TypeVehicleEnum typeVehicle, String brand, String model, String details, boolean isAvailable, List<Rent> rentals) {
        this.id = id;
        this.typeVehicle = typeVehicle;
        this.brand = brand;
        this.model = model;
        this.details = details;
        this.isAvailable = isAvailable;
        this.rentals = rentals;
    }

    public Vehicle() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<Rent> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rent> rentals) {
        this.rentals = rentals;
    }

    public TypeVehicleEnum getTypeVehicle() {
        return typeVehicle;
    }

    public void setTypeVehicle(TypeVehicleEnum typeVehicle) {
        typeVehicle = typeVehicle;
    }
}
