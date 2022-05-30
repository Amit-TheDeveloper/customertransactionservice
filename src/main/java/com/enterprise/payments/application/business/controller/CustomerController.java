package com.enterprise.payments.application.business.controller;

import com.enterprise.payments.api.CustomerServiceApi;
import com.enterprise.payments.application.business.model.Customer;
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
 * Customer Service controller to handle customer activity.
 *
 * @author amit chandra
 */

@RestController
@RequestMapping("/customer")
public class CustomerController implements CustomerServiceApi {

    @Autowired
    CustomerService customerService;

    /**
     * Register a new customer.
     *
     * @param customer
     * @return
     */
    @Override
    public ResponseEntity<?> register(@RequestBody @Valid Customer customer) {
        try {
            Customer response = customerService.register(customer);
            return new ResponseEntity<Customer>(response, HttpStatus.CREATED);
        } catch (CustomerTransactionException _exp) {
            return new ResponseEntity<>(_exp.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Find a customer by id.
     *
     * @param customerId
     * @return
     */
    @Override
    public ResponseEntity<?> findById(@PathVariable Long customerId) {
        try {
            Customer response = customerService.findById(customerId);
            if (response != null) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(MessageUtils.CUSTOMER_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
        } catch (NoElementFoundException _exp) {
            return new ResponseEntity<>(_exp.getMessage(), HttpStatus.NOT_FOUND);
        } catch (CustomerTransactionException _exp) {
            return new ResponseEntity<>(_exp.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Delete a customer by Id.
     *
     * @param customerId
     * @return
     */
    @Override
    public ResponseEntity<?> deleteById(@PathVariable Long customerId) {
        try {
            boolean isDeleted = customerService.deleteById(customerId);
            if (isDeleted) {
                return new ResponseEntity<>(MessageUtils.CUSTOMER_DELETED, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(MessageUtils.CUSTOMER_NOT_DELETED, HttpStatus.EXPECTATION_FAILED);
            }
        } catch (NoElementFoundException _exp) {
            return new ResponseEntity<>(_exp.getMessage(), HttpStatus.NOT_FOUND);
        } catch (CustomerTransactionException _exp) {
            return new ResponseEntity<>(_exp.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Find all customers.
     *
     * @return
     */
    @Override
    public ResponseEntity<?> findAll() {
        try {
            List<Customer> result = customerService.findAll();
            if (result.size() > 0) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(MessageUtils.CUSTOMER_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
        } catch (NoElementFoundException _exp) {
            return new ResponseEntity<>(_exp.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
