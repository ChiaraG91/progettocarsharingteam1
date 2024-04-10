package com.team1.progettocarsharingteam1.dto;

import java.time.LocalDateTime;

public class RentCleanDTO {
    private LocalDateTime startTme;
    private LocalDateTime endTime;
    private Double price;
    private boolean isActive;
    private UserDTO userDTO;
    private VehicleDTO vehicleDTO;

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

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public VehicleDTO getVehicleDTO() {
        return vehicleDTO;
    }

    public void setVehicleDTO(VehicleDTO vehicleDTO) {
        this.vehicleDTO = vehicleDTO;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
