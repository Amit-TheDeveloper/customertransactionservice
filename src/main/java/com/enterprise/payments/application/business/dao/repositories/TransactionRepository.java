package com.enterprise.payments.application.business.dao.repositories;

import com.enterprise.payments.application.business.dao.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Transaction Repository.
 *
 * @author amit chandra
 */
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    //public Set<TransactionEntity> findByAccountId(Long accountId);
}
