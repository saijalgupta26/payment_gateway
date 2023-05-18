/*
package com.example.stripe.controller;

import com.example.stripe.dto.Data;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class Controller {
    @Value("${stripe.apikey}")
    String stripekey;

    @RequestMapping("/create")
    public Data create(@RequestBody Data data) throws StripeException {
        Stripe.apiKey = stripekey;
        Map<String, Object> params = new HashMap<>();
        params.put("name", data.getName());
        params.put("email", data.getEmail());

        Customer customer = Customer.create(params);
        */
/*data.setId(customer.getId());*//*

        return data;

    }

    @RequestMapping("/getAll")
    public List<Data> getAll() throws StripeException {
        Stripe.apiKey = stripekey;
        Map<String, Object> params = new HashMap<>();
        params.put("limit", 3);

        CustomerCollection customers = Customer.list(params);
        List<Data> allCustomer= new ArrayList<>();
        for(int i=0;i<customers.getData().size();i++)
        {
            Data data=new Data();
            */
/**//*
*/
/*data.setId(customers.getData().get(i).getId());*//*

            data.setName(customers.getData().get(i).getName());
            data.setEmail(customers.getData().get(i).getEmail());
            allCustomer.add(data);

        }
        return allCustomer;

    }
    @RequestMapping("/get")
    public Data getCustomer(@RequestParam String id) throws StripeException {
        Stripe.apiKey = stripekey;
        Customer customer = Customer.retrieve(id);
        Data data=new Data();
        data.setEmail(customer.getEmail());
        data.setName(customer.getName());
       */
/* data.setId(customer.getId());*//*

        return data;

    }
}

*/
