package com.team1.progettocarsharingteam1.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StripePaymentProcessor {

    @Value("${stripeApiKey}")
    private String stripeApiKey;

    public StripePaymentProcessor() {
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }


    public void chargePayment(Long id, String token, Double amount, String currency, String description) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("id",id);
        params.put("amount", (int) (amount * 100)); // Converti l'importo in centesimi
        params.put("currency", currency);
        params.put("description", description);
        params.put("source", token); // Token generato dal frontend

        Charge.create(params);

    }
}
