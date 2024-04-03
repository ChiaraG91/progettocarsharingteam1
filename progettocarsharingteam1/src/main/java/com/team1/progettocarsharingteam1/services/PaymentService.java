package com.team1.progettocarsharingteam1.services;

import com.stripe.exception.StripeException;
import com.team1.progettocarsharingteam1.entities.PaymentRequest;
import com.team1.progettocarsharingteam1.entities.enums.ChargeEnum;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class PaymentService {

    @Autowired
    private StripePaymentProcessor stripePaymentProcessor;
    public double calculateAmount(ChargeEnum chargeEnum,  double rentalTime) {
        return chargeEnum.getChargeEnum() * rentalTime;
    }

    public void processPayment(PaymentRequest paymentRequest) throws StripeException {
        double amount = calculateAmount(paymentRequest.getChargeEnum(), paymentRequest.getRentalTime());
        stripePaymentProcessor.chargePayment(paymentRequest.getId(),paymentRequest.getToken(), amount, paymentRequest.getCurrency(), paymentRequest.getDescription());
    }

}
