package com.team1.progettocarsharingteam1.controllers;

import com.stripe.exception.StripeException;
import com.team1.progettocarsharingteam1.entities.PaymentRequest;
import com.team1.progettocarsharingteam1.services.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jdk.jfr.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

    @Operation(summary = "perform the payment")
    @Description("the endpoint redirect you to the Stripe website to confirm payment and return error if the payment its not completed")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful payment"),
            @ApiResponse(responseCode = "404", description = "failed payment")
    })
    @PostMapping("/process/payment")
    public String processPayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            paymentService.processPayment(paymentRequest);
            LOGGER.info("operation completed");
            return "Pagamento elaborato con successo!";
        } catch (StripeException e) {
            LOGGER.error("An error occurred");
            return "Errore durante l'elaborazione del pagamento: " + e.getMessage();
        }
    }
}
