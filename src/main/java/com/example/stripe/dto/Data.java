package com.example.stripe.dto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@lombok.Data
public class Data  {
    private static final long serialVersionUID = 6132714568417076034L;
    private AddressRequest address;
    private Card card;
    private PaymentRequest cardResponse;
    private CustomerDetails customerDetails;
    private Payment payment;
}
