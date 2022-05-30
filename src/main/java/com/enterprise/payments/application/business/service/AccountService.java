package com.enterprise.payments.application.business.service;


import com.enterprise.payments.application.business.model.Account;
import com.enterprise.payments.application.business.model.AccountType;
import com.enterprise.payments.application.business.model.Customer;
import com.enterprise.payments.application.business.model.Transaction;
import com.enterprise.payments.application.exception.CustomerTransactionException;
import com.enterprise.payments.application.exception.NoElementFoundException;

import java.util.List;

/**
 * Account Service.
 *
 * @author amit chandra
 */
public interface AccountService {

    Account open(Long CustomerId, AccountType accountType) throws CustomerTransactionException;

    Account findById(Long accountId) throws NoElementFoundException;

    Customer findCustomerByAccountId(Long accountId) throws NoElementFoundException;

    boolean deleteById(Long accountId) throws CustomerTransactionException;

    boolean doTransaction(Transaction transaction) throws CustomerTransactionException;

    List<Transaction> findTransactionsByAccountId(Long accountId) throws NoElementFoundException;

    List<Account> findAll() throws NoElementFoundException;

    String getAccountBalance(Long accountId) throws CustomerTransactionException;
}
