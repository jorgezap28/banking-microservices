package com.banca.cliente.persona.repository;

import com.banca.cliente.persona.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
