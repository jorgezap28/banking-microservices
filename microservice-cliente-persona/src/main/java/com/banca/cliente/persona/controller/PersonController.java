package com.banca.cliente.persona.controller;

import com.banca.cliente.persona.domain.Person;
import com.banca.cliente.persona.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping
        public ResponseEntity<Person> createPerson(@RequestBody Person person) {
            person.setRegistrationDate(java.time.LocalDate.now());
            return ResponseEntity.ok(personRepository.save(person));
        }
}
