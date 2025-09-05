package com.banca.cuenta.movimientos.repository;

import com.banca.cuenta.movimientos.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @class_name AccountRepository.java
 * @class_description Repository interface for Account entity.
 * @author
 * @create_date 2025-08-20
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByNumber(String number);
}
