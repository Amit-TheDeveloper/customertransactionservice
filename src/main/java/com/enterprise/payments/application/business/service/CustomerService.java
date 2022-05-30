package com.enterprise.payments.application.business.service;

import com.enterprise.payments.application.business.model.Customer;
import com.enterprise.payments.application.exception.CustomerTransactionException;
import com.enterprise.payments.application.exception.NoElementFoundException;

import java.util.List;

/**
 * Customer Service.
 *
 * @author amit chandra
 */
public interface CustomerService {


    Customer register(Customer customer) throws CustomerTransactionException;

    Customer findById(Long id) throws NoElementFoundException, CustomerTransactionException;

    boolean deleteById(Long id) throws CustomerTransactionException;

    List<Customer> findAll() throws NoElementFoundException;

}
