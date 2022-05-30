package com.enterprise.payments.application.business.dao.repositories;

import com.enterprise.payments.application.business.dao.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Account Repository.
 *
 * @author amit chandra
 */

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
