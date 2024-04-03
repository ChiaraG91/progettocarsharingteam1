package com.team1.progettocarsharingteam1.services;

import com.stripe.exception.StripeException;
import com.team1.progettocarsharingteam1.entities.PaymentRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class PaymentService {

    @Autowired
    private StripePaymentProcessor stripePaymentProcessor;
    public double calculateAmount(double hourlyRate, double durationHours) {
        double amount = hourlyRate * durationHours;
        return amount;
    }

    public void processPayment(PaymentRequest paymentRequest) throws StripeException {
        double amount = calculateAmount(paymentRequest.getChargeEnum(), paymentRequest.getRentalTime());
        stripePaymentProcessor.chargePayment(paymentRequest.getId(),paymentRequest.getToken(), amount, paymentRequest.getCurrency(), paymentRequest.getDescription());
    }

}
