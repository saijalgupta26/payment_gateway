package com.example.stripe.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Document
@Slf4j
@Data
public class CustomerDetails {
    private String name;
    private String email;
    private  String id;


}
