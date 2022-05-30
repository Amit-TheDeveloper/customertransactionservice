package com.enterprise.payments.api;

import com.enterprise.payments.application.business.model.AccountType;
import com.enterprise.payments.application.business.model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface AccountServiceApi {

    /**
     * open an account for a customer.
     *
     * @param customerID
     * @return
     */
    @PostMapping(value = "/{customerID}/{accountType}/open")
    public ResponseEntity<?> open(@PathVariable Long customerID, @PathVariable AccountType accountType);

    /**
     * Find an account by it's id.
     *
     * @param accountId
     * @return
     */
    @GetMapping(value = "/{accountId}")
    public ResponseEntity<?> findById(@PathVariable Long accountId);

    /**
     * Find a customer by account id.
     *
     * @param accountId
     * @return
     */
    @GetMapping(value = "/{accountId}/customer")
    public ResponseEntity<?> findCustomerByAccountId(@PathVariable Long accountId);

    /**
     * Make a transaction to an account
     *
     * @param transaction
     * @return
     */
    @PostMapping(value = "/transaction")
    public ResponseEntity<String> transaction(@RequestBody @Valid Transaction transaction);

    /**
     * deletes an account by it's Id.
     *
     * @param accountId
     * @return
     */
    @DeleteMapping(value = "/{accountId}")
    public ResponseEntity<String> delete(@PathVariable Long accountId);

    /**
     * Find transactions by account id.
     *
     * @return
     */
    @GetMapping(value = "{accountId}/transactions")
    public ResponseEntity<?> findTransactionsByAccountId(@PathVariable Long accountId);

    /**
     * Find all accounts.
     *
     * @return
     */
    @GetMapping(value = "/all")
    public ResponseEntity<?> findAll();

    @GetMapping(value = "/{accountId}/balance")
    public ResponseEntity<?> getAccountBalance(@PathVariable Long accountId);

}
