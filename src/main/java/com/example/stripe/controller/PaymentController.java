/*
package com.example.stripe.controller;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Address;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.SetupIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Slf4j
public class PaymentController {
    @Value("${stripe.apikey}")
    String stripekey;
    @RequestMapping("/createPayment")
    public String createPayment() throws StripeException {
        Stripe.apiKey = stripekey;

        Map<String, Object> card = new HashMap<>();
        card.put("number", "4242424242424242");
        card.put("exp_month", 8);
        card.put("exp_year", 2024);
        card.put("cvc", "314");
        Map<String, Object> params = new HashMap<>();
        params.put("type", "card");
        params.put("card", card);

        PaymentMethod paymentMethod =
                PaymentMethod.create(params);
        return paymentMethod.getId();

    }
    @RequestMapping("/payment")
    public String PaymentByusingCustomer() throws StripeException {
        Stripe.apiKey=stripekey;
        PaymentMethod paymentMethod =
                PaymentMethod.retrieve(
                        "pm_1N3E68SJ0Ui9Y1ZQVT701tVn"
                );

        Map<String, Object> params = new HashMap<>();
        params.put("customer", "cus_Norn2akT8FzcfX");

        PaymentMethod updatedPaymentMethod = paymentMethod.attach(params);

        return updatedPaymentMethod.getId();
    }
    @PostMapping("/create-payment-intent")
    public String createPaymentIntent(@RequestBody Payment_Intent payment_intent)throws StripeException {
        Stripe.apiKey = "sk_test_51N1UtFSJ0Ui9Y1ZQI5KKZPMeUftW0az3WRCgrrLIRBAuu6b6FGTgRK0DgVFPhCjlf7sRIhEwPRN4IwWzrnH4ayRr00bvCQvaSp";

        String customerId = "cus_Norn2akT8FzcfX";
        String paymentMethodId = "pm_1N3E68SJ0Ui9Y1ZQVT701tVn";
        int amount = 20000;

        Map<String, Object> params = new HashMap<>();

        params.put("amount", payment_intent.getAmount());
        params.put("currency", "usd");
        params.put("customer",customerId);
        params.put("payment_method", paymentMethodId);
        params.put("off_session", true);
        params.put("confirm", true);
        params.put("description","transaction");
        params.put("payment_method_types", Arrays.asList("card"));
        params.put("shipping", new HashMap<String, Object>() {{
            put("name", "Customer Name");
            put("address", new HashMap<String, Object>() {{
                put("line1", "123 Main St");
                put("city", "Newyork");
                put("state", "NY");
                put("postal_code", "90001");
                put("country", "US");
            }});
        }});
        PaymentIntent intent = PaymentIntent.create(params);
        return intent.getId();
    }

}
*/
