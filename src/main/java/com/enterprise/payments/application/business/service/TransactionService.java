package com.enterprise.payments.application.business.service;


import com.enterprise.payments.application.business.model.Transaction;
import com.enterprise.payments.application.exception.CustomerTransactionException;
import com.enterprise.payments.application.exception.NoElementFoundException;

/**
 * Transaction Service.
 *
 * @author amit chandra
 */
public interface TransactionService {

    Transaction findById(Long transactionId) throws NoElementFoundException, CustomerTransactionException;

    boolean deleteById(Long transactionId) throws CustomerTransactionException;

}