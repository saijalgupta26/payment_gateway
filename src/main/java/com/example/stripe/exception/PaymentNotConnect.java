package com.example.stripe.exception;

public class PaymentNotConnect extends Exception {

    public PaymentNotConnect(String error) {
        super(error);
    }
}
