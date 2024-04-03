package com.team1.progettocarsharingteam1.controllers;

import com.stripe.exception.StripeException;
import com.team1.progettocarsharingteam1.entities.PaymentRequest;
import com.team1.progettocarsharingteam1.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stripe")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process/payment")
    public String processPayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            paymentService.processPayment(paymentRequest);
            return "Pagamento elaborato con successo!";
        } catch (StripeException e) {
            return "Errore durante l'elaborazione del pagamento: " + e.getMessage();
        }
    }
}
