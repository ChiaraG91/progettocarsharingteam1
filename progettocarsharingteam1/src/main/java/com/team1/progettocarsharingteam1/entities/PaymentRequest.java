package com.team1.progettocarsharingteam1.entities;

import com.team1.progettocarsharingteam1.entities.enums.ChargeEnum;
import jakarta.persistence.*;

@Entity
public class PaymentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token; // Token generato dal frontend
    private ChargeEnum chargeEnum; //Tariffa oraria
    @Column(nullable = false)
    private double rentalTime; // Durata in ore del servizio
    private String currency; // Valuta del pagamento
    private String description; // Descrizione del pagamento


    public PaymentRequest() {
    }

    public PaymentRequest(Long id, String token, ChargeEnum chargeEnum, double rentalTime, String currency, String description) {
        this.id = id;
        this.token = token;
        this.chargeEnum = chargeEnum;
        this.rentalTime = rentalTime;
        this.currency = currency;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ChargeEnum getChargeEnum() {
        return chargeEnum;
    }

    public void setChargeEnum(ChargeEnum chargeEnum) {
        this.chargeEnum = chargeEnum;
    }

    public double getRentalTime() {
        return rentalTime;
    }

    public void setRentalTime(double rentalTime) {
        this.rentalTime = rentalTime;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

