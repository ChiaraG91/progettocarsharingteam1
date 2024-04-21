package com.team1.progettocarsharingteam1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team1.progettocarsharingteam1.entities.User;
import com.team1.progettocarsharingteam1.entities.Vehicle;

import java.time.LocalDateTime;

public class RentDTO {
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private LocalDateTime startTme;
    @JsonIgnore
    private LocalDateTime endTime;
    @JsonIgnore
    private Double price;
    @JsonIgnore
    private boolean isActive;
    private User user;
    private Vehicle vehicle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTme() {
        return startTme;
    }

    public void setStartTme(LocalDateTime startTme) {
        this.startTme = startTme;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
