package com.enterprise.payments.api;

import com.enterprise.payments.application.business.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface CustomerServiceApi {

    /**
     * Register a new customer.
     *
     * @param customer
     * @return
     */
    @PostMapping(value = "/register", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> register(@RequestBody @Valid Customer customer);

    /**
     * Find a customer by id.
     *
     * @param customerId
     * @return
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<?> findById(@PathVariable Long customerId);

    /**
     * Delete a customer by Id.
     *
     * @param customerId
     * @return
     */
    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteById(@PathVariable Long customerId);

    /**
     * Find all customers.
     *
     * @return
     */
    @GetMapping(value = "/all")
    public ResponseEntity<?> findAll();

}
