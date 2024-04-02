package com.team1.progettocarsharingteam1.entities;

import com.team1.progettocarsharingteam1.entities.enums.ChargeEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rentals")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startTme;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private Double price;

    @Enumerated
    private ChargeEnum chargeEnum;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Column(nullable = false)
    private boolean isActive = true;

    public Rent(Long id, LocalDateTime startTme, LocalDateTime endTime, Double price, ChargeEnum chargeEnum, User user, Vehicle vehicle) {
        this.id = id;
        this.startTme = startTme;
        this.endTime = endTime;
        this.price = price;
        this.chargeEnum = chargeEnum;
        this.user = user;
        this.vehicle = vehicle;
    }

    public Rent() {
    }

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

    public ChargeEnum getChargeEnum() {
        return chargeEnum;
    }

    public void setChargeEnum(ChargeEnum chargeEnum) {
        this.chargeEnum = chargeEnum;
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
