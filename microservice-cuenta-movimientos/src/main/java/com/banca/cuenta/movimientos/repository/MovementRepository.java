package com.banca.cuenta.movimientos.repository;

import com.banca.cuenta.movimientos.domain.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<Movement, Long> {
	Movement findTopByAccountIdOrderByIdDesc(Long accountId);
}
