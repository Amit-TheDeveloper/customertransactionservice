package com.enterprise.payments.application.business.controller;


import com.enterprise.payments.api.AccountServiceApi;
import com.enterprise.payments.application.business.model.Account;
import com.enterprise.payments.application.business.model.AccountType;
import com.enterprise.payments.application.business.model.Customer;
import com.enterprise.payments.application.business.model.Transaction;
import com.enterprise.payments.application.business.service.AccountService;
import com.enterprise.payments.application.business.service.CustomerService;
import com.enterprise.payments.application.business.utils.MessageUtils;
import com.enterprise.payments.application.exception.CustomerTransactionException;
import com.enterprise.payments.application.exception.NoElementFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Account Service controller to handle account activity.
 *
 * @author amit chandra
 */
@RestController
@RequestMapping(value = "/account")
public class AccountController implements AccountServiceApi {

    @Autowired
    AccountService accountService;

    @Autowired
    CustomerService customerService;

    /**
     * open an account for a customer.
     *
     * @param customerID
     * @return
     */
    @Override
    public ResponseEntity<?> open(@PathVariable Long customerID, @PathVariable AccountType accountType) {
        try {
            final Account account = accountService.open(customerID, accountType);
            return new ResponseEntity<Account>(account, HttpStatus.CREATED);
        } catch (NoElementFoundException _exp) {
            return new ResponseEntity<>(_exp.getMessage(), HttpStatus.NOT_FOUND);
        } catch (CustomerTransactionException _exp) {
            return new ResponseEntity<>(_exp.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Find an account by it's id.
     *
     * @param accountId
     * @return
     */
    @Override
    public ResponseEntity<?> findById(@PathVariable Long accountId) {
        try {
            final Account account = accountService.findById(accountId);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (NoElementFoundException _exp) {
            return new ResponseEntity<>(_exp.getMessage(), HttpStatus.NOT_FOUND);
        } catch (CustomerTransactionException _exp) {
            return new ResponseEntity<>(_exp.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Find a customer by account id.
     *
     * @param accountId
     * @return
     */
    @Override
    public ResponseEntity<?> findCustomerByAccountId(@PathVariable Long accountId) {
        try {
            final Customer customer = accountService.findCustomerByAccountId(accountId);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (CustomerTransactionException _exp) {
            return new ResponseEntity<>(_exp.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * make a transaction to an account
     *
     * @param transaction
     * @return
     */
    @Override
    public ResponseEntity<String> transaction(@RequestBody @Valid Transaction transaction) {
        try {
            accountService.doTransaction(transaction);
            return new ResponseEntity<>(MessageUtils.SUCCESSFUL_TRANSACTION, HttpStatus.OK);
        } catch (CustomerTransactionException _exp) {
            return new ResponseEntity<>(_exp.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * deletes an account by it's Id.
     *
     * @param accountId
     * @return
     */
    @Override
    public ResponseEntity<String> delete(@PathVariable Long accountId) {
        try {
            boolean result = accountService.deleteById(accountId);
            if (true) {
                return new ResponseEntity<>("Delete", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(MessageUtils.ACCOUNT_NOT_DELETED, HttpStatus.EXPECTATION_FAILED);
            }
        } catch (NoElementFoundException _exp) {
            return new ResponseEntity<>(_exp.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Find transactions by account id.
     *
     * @return
     */
    @Override
    public ResponseEntity<?> findTransactionsByAccountId(@PathVariable Long accountId) {
        try {
            final List<Transaction> result = accountService.findTransactionsByAccountId(accountId);
            if (result.size() > 0) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(MessageUtils.NO_TRANSACTION_FOUND, HttpStatus.NOT_FOUND);
            }
        } catch (NoElementFoundException _exp) {
            return new ResponseEntity<>(_exp.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Find all accounts.
     *
     * @return
     */
    @Override
    public ResponseEntity<?> findAll() {
        try {
            final List<Account> result = accountService.findAll();
            if (result.size() > 0) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(MessageUtils.NO_ACCOUNT_FOUND, HttpStatus.NOT_FOUND);
            }
        } catch (NoElementFoundException _exp) {
            return new ResponseEntity<>(_exp.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get Account Balance
     *
     * @param accountId
     * @return
     */
    @Override
    public ResponseEntity<?> getAccountBalance(@PathVariable Long accountId) {
        try {
            String balance = accountService.getAccountBalance(accountId);
            return new ResponseEntity<>(balance, HttpStatus.OK);
        } catch (CustomerTransactionException _exp) {
            return new ResponseEntity<>(_exp.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
