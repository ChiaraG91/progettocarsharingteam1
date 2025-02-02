package com.team1.progettocarsharingteam1.dto;

import java.time.LocalDateTime;

public class RentCleanDTO {
    private Long id;
    private LocalDateTime startTme;
    private LocalDateTime endTime;
    private Double price;
    private boolean isActive;
    private UserCleanDTO userCleanDTO;
    private VehicleDTO vehicleDTO;

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

    public UserCleanDTO getUserCleanDTO() {
        return userCleanDTO;
    }

    public void setUserCleanDTO(UserCleanDTO userCleanDTO) {
        this.userCleanDTO = userCleanDTO;
    }
}
