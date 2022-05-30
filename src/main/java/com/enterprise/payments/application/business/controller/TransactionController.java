package com.enterprise.payments.application.business.controller;


import com.enterprise.payments.api.TransactionServiceApi;
import com.enterprise.payments.application.business.model.Transaction;
import com.enterprise.payments.application.business.service.TransactionService;
import com.enterprise.payments.application.business.utils.MessageUtils;
import com.enterprise.payments.application.exception.NoElementFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Transaction Service controller to handle customer activity.
 *
 * @author amit chandra
 */
@RestController
@RequestMapping(value = "/transaction")
public class TransactionController implements TransactionServiceApi {

    @Autowired
    TransactionService transactionService;

    /**
     * Find a transaction by Id
     *
     * @param transactionId
     * @return
     */
    @Override
    public ResponseEntity<?> findById(@PathVariable Long transactionId) {
        try {
            Transaction transaction = transactionService.findById(transactionId);
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } catch (NoElementFoundException _exp) {
            return new ResponseEntity<>(MessageUtils.NO_TRANSACTION_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes Transaction By it's Id
     * @param transactionId
     * @return
     */
    @Override
    public ResponseEntity<?> deleteById(@PathVariable Long transactionId) {
        try {
            boolean result = transactionService.deleteById(transactionId);
            if (result) {
                return new ResponseEntity<>(MessageUtils.TRANSACTION_DELETED, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(MessageUtils.TRANSACTION_NOT_DELETED, HttpStatus.EXPECTATION_FAILED);
            }
        } catch (NoElementFoundException _exp) {
            return new ResponseEntity<>(_exp.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
