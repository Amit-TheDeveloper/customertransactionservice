package com.enterprise.payments.application.business.dao.repositories;

import com.enterprise.payments.application.business.dao.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Customer Repository.
 *
 * @author amit chandra
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
