package com.enterprise.payments.application.business.service.impl;


import com.enterprise.payments.application.business.dao.entity.AccountEntity;
import com.enterprise.payments.application.business.dao.entity.CustomerEntity;
import com.enterprise.payments.application.business.dao.entity.TransactionEntity;
import com.enterprise.payments.application.business.dao.repositories.AccountRepository;
import com.enterprise.payments.application.business.dao.repositories.CustomerRepository;
import com.enterprise.payments.application.business.dao.repositories.TransactionRepository;
import com.enterprise.payments.application.business.model.*;
import com.enterprise.payments.application.business.service.AccountService;
import com.enterprise.payments.application.business.utils.MessageUtils;
import com.enterprise.payments.application.exception.CustomerTransactionException;
import com.enterprise.payments.application.exception.NoElementFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Account Service layer to handle account activity.
 *
 * @author amit chandra
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ModelMapper mapper;

    /**
     * Service handler to Open Customer account.
     *
     * @param customerId
     * @return
     * @throws CustomerTransactionException
     */
    @Override
    public Account open(final Long customerId, final AccountType accountType) throws CustomerTransactionException {
        try {
            AccountEntity accountDao = new AccountEntity();
            CustomerEntity customer = customerRepository.findById(customerId).get();
            accountDao.setCustomerId(customerId);
            accountDao.setCreatedOn(new Date());
            accountDao.setAccountType(accountType);
            AccountEntity accountPersisted = accountRepository.save(accountDao);
            return mapper.map(accountPersisted, Account.class);
        } catch (NoSuchElementException _exp) {
            throw new NoElementFoundException(MessageUtils.CUSTOMER_NOT_FOUND);
        } catch (RuntimeException _exp) {
            LOGGER.error(MessageUtils.ACCOUNT_OPENING_ERROR);
            throw new CustomerTransactionException(MessageUtils.ACCOUNT_OPENING_ERROR);
        }
    }

    /**
     * Find account by Id
     *
     * @param id
     * @return
     * @throws NoElementFoundException
     * @throws CustomerTransactionException
     */
    @Override
    public Account findById(Long id) throws NoElementFoundException, CustomerTransactionException {
        try {
            final Optional<AccountEntity> account = accountRepository.findById(id);
            if (account.isPresent()) {
                return mapper.map(account.get(), Account.class);
            } else {
                throw new NoElementFoundException(MessageUtils.NO_ACCOUNT_FOUND);
            }
        } catch (CustomerTransactionException _exp) {
            LOGGER.error(_exp.getMessage());
            throw _exp;
        }
    }

    /**
     * Find a customer by account Id.
     *
     * @param accountId
     * @return
     * @throws NoElementFoundException
     */
    @Override
    public Customer findCustomerByAccountId(final Long accountId) throws NoElementFoundException {
        try {
            final Optional<AccountEntity> account = accountRepository.findById(accountId);
            if (account.isPresent()) {
                if (account.isPresent() && account.get().getCustomerId() != null) {
                    Optional<CustomerEntity> customerDao = customerRepository.findById(account.get().getCustomerId());
                    return mapper.map(customerDao, Customer.class);
                }
                {
                    throw new NoElementFoundException(MessageUtils.CUSTOMER_NOT_FOUND);
                }
            } else {
                throw new NoElementFoundException(MessageUtils.NO_ACCOUNT_FOUND);
            }
        } catch (CustomerTransactionException _exp) {
            LOGGER.error(_exp.getMessage());
            throw _exp;
        }
    }

    /**
     * Delete Account by Id.
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(final Long id) {
        try {
            accountRepository.deleteById(id);
        } catch (RuntimeException _exp) {
            LOGGER.error(_exp.getMessage());
            throw new CustomerTransactionException(MessageUtils.ACCOUNT_NOT_DELETED);
        }
        return true;
    }


    /**
     * Do Transaction on an account
     *
     * @param transaction
     * @return
     * @throws CustomerTransactionException
     */
    @Override
    public boolean doTransaction(final Transaction transaction) throws CustomerTransactionException {
        Long accountId = transaction.getAccount().getAccountId();
        if (accountId != null) {
            if (isValidAccount(accountId)) {
                TransactionEntity transactionDao = new TransactionEntity();
                transactionDao.setTxnType(transaction.getTxnType());
                transactionDao.setAmount(transaction.getAmount());
                transactionDao.setAccountId(accountId);
                transactionDao.setCreatedOn(new Date());
                transactionRepository.save(transactionDao);
                updateBalance(transaction, accountId);
            } else {
                throw new CustomerTransactionException(MessageUtils.NO_ACCOUNT_FOUND);
            }
        }
        return true;
    }

    private void updateBalance(final Transaction transaction, final Long accountId) {

        final Optional<AccountEntity> accountDao = accountRepository.findById(accountId);
        calculateBalance(transaction, accountDao);
        accountRepository.save(accountDao.get());
    }

    /**
     * Returns true if account is present
     *
     * @param accountId
     * @return
     */
    private boolean isValidAccount(Long accountId) {
        final Optional<AccountEntity> accountDao = accountRepository.findById(accountId);
        return accountDao.isPresent();
    }

    /**
     * Find account Transactions.
     *
     * @param accountId
     * @return
     * @throws NoElementFoundException
     */
    @Override
    public List<Transaction> findTransactionsByAccountId(Long accountId) throws NoElementFoundException {
        final Optional<AccountEntity> accountDao = accountRepository.findById(accountId);
        if (accountDao.isPresent() && (accountDao.get().getTransactions() != null && accountDao.get().getTransactions().size() > 0)) {
            List<Transaction> accounts = Arrays.asList(mapper.map(accountDao.get().getTransactions(), Transaction[].class));
            return accounts;
        } else {
            throw new NoElementFoundException(MessageUtils.NO_TRANSACTION_FOUND);
        }
    }

    /**
     * Find all accounts
     *
     * @return
     * @throws NoElementFoundException
     */
    @Override
    public List<Account> findAll() throws NoElementFoundException {
        List<AccountEntity> accountDaos = accountRepository.findAll();
        if (accountDaos != null && accountDaos.size() > 0) {
            List<Account> accounts = Arrays.asList(mapper.map(accountDaos, Account[].class));
            return accounts;
        } else {
            throw new NoElementFoundException(MessageUtils.NO_ACCOUNT_FOUND);
        }
    }

    /**
     * Return Account balance
     *
     * @param accountId
     * @return
     * @throws CustomerTransactionException
     */
    @Override
    public String getAccountBalance(final Long accountId) throws CustomerTransactionException {
        final Optional<AccountEntity> accountDao = accountRepository.findById(accountId);
        if (accountDao.isPresent()) {
            Double balance = accountDao.get().getBalance();
            return MessageUtils.ACCOUNT_BALANCE + balance;
        } else {
            throw new CustomerTransactionException(MessageUtils.ACCOUNT_BALANCE_NOT_FOUND);
        }
    }

    /**
     * calculate balance amount
     *
     * @param transaction
     * @param accountDao
     */
    private void calculateBalance(final Transaction transaction, final Optional<AccountEntity> accountDao) {
        if (transaction.getTxnType() == TransactionType.CREDIT) {
            Double balance = Double.sum(accountDao.get().getBalance(), transaction.getAmount());
            Double roundedBalance = BigDecimal.valueOf(balance).setScale(2, RoundingMode.HALF_UP).doubleValue();
            accountDao.get().setBalance(roundedBalance);
        } else {
            BigDecimal balance = BigDecimal.valueOf(accountDao.get().getBalance()).subtract(BigDecimal.valueOf(transaction.getAmount()));
            Double roundedBalance = BigDecimal.valueOf(balance.doubleValue()).setScale(2, RoundingMode.HALF_UP).doubleValue();
            accountDao.get().setBalance(roundedBalance);
        }
    }
}
