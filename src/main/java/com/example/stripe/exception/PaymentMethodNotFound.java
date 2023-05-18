package com.example.stripe.exception;

import java.io.Serial;

public class PaymentMethodNotFound extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;
    public PaymentMethodNotFound(String error) {
        super(error);
    }
}
