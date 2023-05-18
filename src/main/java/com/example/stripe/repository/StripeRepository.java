package com.example.stripe.repository;

import com.example.stripe.dto.CustomerDetails;
import com.example.stripe.dto.Data;
import com.stripe.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StripeRepository extends MongoRepository<CustomerDetails,String> {

    Optional<CustomerDetails> findByemail(String email);
}
