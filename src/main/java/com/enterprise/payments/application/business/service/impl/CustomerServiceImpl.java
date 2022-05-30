package com.enterprise.payments.application.business.service.impl;

import com.enterprise.payments.application.business.dao.entity.CustomerEntity;
import com.enterprise.payments.application.business.dao.repositories.CustomerRepository;
import com.enterprise.payments.application.business.dao.repositories.TransactionRepository;
import com.enterprise.payments.application.business.model.Customer;
import com.enterprise.payments.application.business.service.CustomerService;
import com.enterprise.payments.application.business.utils.MessageUtils;
import com.enterprise.payments.application.exception.CustomerTransactionException;
import com.enterprise.payments.application.exception.NoElementFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Customer Service layer to handle customer activity.
 *
 * @author amit chandra
 */

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ModelMapper mapper;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TransactionRepository transactionRepository;

    /**
     * Registers a new customer.
     *
     * @param customer
     * @return
     */
    @Override
    public Customer register(Customer customer) throws CustomerTransactionException {
        try {
            customer.setCreatedOn(new Date());
            CustomerEntity customerDao = mapper.map(customer, CustomerEntity.class);
            CustomerEntity customerPersisted = customerRepository.save(customerDao);
            return mapper.map(customerPersisted, Customer.class);
        }catch (RuntimeException _exp) {
            LOGGER.error(_exp.getMessage());
            throw new CustomerTransactionException(_exp.getMessage());
        }
    }

    /**
     * Find a customer by Id.
     *
     * @param id
     * @return
     * @throws NoElementFoundException
     * @throws CustomerTransactionException
     */
    @Override
    public Customer findById(Long id) throws NoElementFoundException, CustomerTransactionException {
        try {
            Optional<CustomerEntity> customerDao = customerRepository.findById(id);
            if (customerDao.isPresent()) {
                return mapper.map(customerDao.get(), Customer.class);
            } else {
                throw new NoElementFoundException(MessageUtils.CUSTOMER_NOT_FOUND);
            }
        } catch (CustomerTransactionException _exp) {
            LOGGER.error(_exp.getMessage());
            throw _exp;
        }
    }

    /**
     * Deletes a Customer by ID
     *
     * @param id
     * @return
     * @throws CustomerTransactionException
     */
    @Override
    public boolean deleteById(Long id) throws CustomerTransactionException {
        try {
            customerRepository.deleteById(id);
            return true;
        } catch (RuntimeException _exp) {
            LOGGER.error(_exp.getMessage());
            throw new CustomerTransactionException(MessageUtils.CUSTOMER_NOT_DELETED);
        }
    }

    /**
     * Finds all customers
     *
     * @return
     * @throws NoElementFoundException
     */
    @Override
    public List<Customer> findAll() throws NoElementFoundException {
        List<CustomerEntity> customerDaos = customerRepository.findAll();
        if (customerDaos != null && customerDaos.size() > 0) {
            List<Customer> customers = Arrays.asList(mapper.map(customerDaos, Customer[].class));
            return customers;
        } else {
            throw new NoElementFoundException(MessageUtils.CUSTOMER_NOT_FOUND);
        }
    }

}
