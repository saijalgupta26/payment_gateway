package com.example.stripe.controller;

import com.example.stripe.dto.CustomerDetails;
import com.example.stripe.dto.Data;
import com.example.stripe.exception.CustomerNotFoundException;
import com.example.stripe.exception.PaymentMethodNotFound;
import com.example.stripe.service.StripePaymentService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StripePayment {
    @Autowired
    private StripePaymentService stripePaymentService;
    @Autowired
    Data data;

    @PostMapping("stripePayment")
    public ResponseEntity<?> stripePayment(@RequestBody Data data) throws StripeException {

        String customerId = stripePaymentService.customerCreate(data.getCustomerDetails());
        String paymentMethod = stripePaymentService.paymentCreate(data.getCard());
        String paymentId = stripePaymentService.paymentWithCustomer(customerId, paymentMethod,data.getPayment());
        stripePaymentService.paymentIntent(customerId,paymentMethod,data.getCardResponse(),data.getAddress(),data.getCustomerDetails().getName());
        return new ResponseEntity<>("successfully", HttpStatus.FOUND);
        //name,email
    }
    @PostMapping("customerAlreadyExists")
    public ResponseEntity<?> stripePayment1(@RequestBody Data data) throws StripeException, CustomerNotFoundException {
        String customerId = stripePaymentService.customerAlreadyExists(data.getCustomerDetails());

        String paymentMethod = stripePaymentService.paymentCreate(data.getCard());
        String paymentId = stripePaymentService.paymentWithCustomer(customerId, paymentMethod,data.getPayment());
        stripePaymentService.paymentIntent(customerId,paymentMethod,data.getCardResponse(),data.getAddress(),data.getCustomerDetails().getName());

        return new ResponseEntity<>("successfully", HttpStatus.FOUND);

    }
    @PostMapping("paymentMethodAlreadyExist")
    public ResponseEntity<?> stripePayment2(@RequestBody Data data) throws CustomerNotFoundException, PaymentMethodNotFound, StripeException {
        String customerId = stripePaymentService.customerAlreadyExists(data.getCustomerDetails());
        String paymentMethod = stripePaymentService.paymentMethodAlreadyCreated(data.getCard());

        stripePaymentService.paymentIntent(customerId,paymentMethod,data.getCardResponse(),data.getAddress(),data.getCustomerDetails().getName());

        return new ResponseEntity<>("successfully", HttpStatus.FOUND);
        

    }


}
