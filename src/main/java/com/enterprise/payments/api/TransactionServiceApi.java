package com.enterprise.payments.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface TransactionServiceApi {

    /**
     * Find a transaction by Id
     *
     * @param transactionId
     * @return
     */
    @GetMapping(value = "{transactionId}")
    public ResponseEntity<?> findById(@PathVariable Long transactionId);

    /**
     * Deletes Transaction By it's Id
     * @param transactionId
     * @return
     */
    @DeleteMapping(value = "{transactionId}")
    public ResponseEntity<?> deleteById(@PathVariable Long transactionId);

    }
