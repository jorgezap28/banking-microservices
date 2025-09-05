package com.banca.cuenta.movimientos.repository;

import com.banca.cuenta.movimientos.domain.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Long> {
}
