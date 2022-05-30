package com.enterprise.payments.application.business.service.impl;


import com.enterprise.payments.application.business.dao.entity.TransactionEntity;
import com.enterprise.payments.application.business.dao.repositories.CustomerRepository;
import com.enterprise.payments.application.business.dao.repositories.TransactionRepository;
import com.enterprise.payments.application.business.model.Transaction;
import com.enterprise.payments.application.business.service.TransactionService;
import com.enterprise.payments.application.business.utils.MessageUtils;
import com.enterprise.payments.application.exception.CustomerTransactionException;
import com.enterprise.payments.application.exception.NoElementFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Transactions Service layer to handle transactions activity.
 *
 * @author amit chandra
 */

@Service
public class TransactionServiceImpl implements TransactionService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ModelMapper mapper;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CustomerRepository customerRepository;

    /**
     * Find Transaction By Id.
     *
     * @param transactionId
     * @return
     * @throws NoElementFoundException
     * @throws CustomerTransactionException
     */
    @Override
    public Transaction findById(Long transactionId) throws NoElementFoundException, CustomerTransactionException {
        try {
            Optional<TransactionEntity> transaction = transactionRepository.findById(transactionId);
            if (transaction.isPresent()) {
                return mapper.map(transaction.get(), Transaction.class);
            } else {
                throw new NoElementFoundException(MessageUtils.NO_TRANSACTION_FOUND);
            }
        } catch (CustomerTransactionException _exp) {
            LOGGER.error(_exp.getMessage());
            throw _exp;
        }
    }

    /**
     * Delete Transaction by Id.
     *
     * @param transactionId
     * @return
     * @throws CustomerTransactionException
     */
    @Override
    public boolean deleteById(Long transactionId) throws CustomerTransactionException {
        try {
            transactionRepository.deleteById(transactionId);
        } catch (RuntimeException _exp) {
            LOGGER.error(_exp.getMessage());
            throw new CustomerTransactionException("Error while deleting Transaction:" + _exp.getMessage());
        }
        return true;
    }

}
