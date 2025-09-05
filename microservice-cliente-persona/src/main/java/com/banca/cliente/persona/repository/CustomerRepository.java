package com.banca.cliente.persona.repository;

import com.banca.cliente.persona.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @class_name CustomerRepository.java
 * @class_description Repository interface for Customer entity.
 * @author
 * @create_date 2025-08-20
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
