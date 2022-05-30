package com.enterprise.payments.application.business.dao.entity;

import com.enterprise.payments.application.business.model.TransactionType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Transaction Dao.
 *
 * @author amit chandra
 */
@Entity
@Table(name = "transactions")
public class TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column
    private Date createdOn;

    @Column
    private TransactionType txnType;

    @Column
    private Double amount;

    @Column(name = "account_id")
    private Long accountId;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public TransactionType getTxnType() {
        return txnType;
    }

    public void setTxnType(TransactionType txnType) {
        this.txnType = txnType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
