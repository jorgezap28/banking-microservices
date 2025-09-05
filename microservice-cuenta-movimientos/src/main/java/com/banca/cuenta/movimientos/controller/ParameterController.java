package com.banca.cuenta.movimientos.controller;

import com.banca.cuenta.movimientos.domain.Parameter;
import com.banca.cuenta.movimientos.repository.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/parameters")
public class ParameterController {

    @Autowired
    private ParameterRepository parameterRepository;

    @PostMapping
    public ResponseEntity<Parameter> createParameter(@RequestBody Parameter parameter) {
        Parameter saved = parameterRepository.save(parameter);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Parameter>> getAllParameters() {
        List<Parameter> activeParams = parameterRepository.findAll()
            .stream()
            .filter(p -> "ACTIVE".equalsIgnoreCase(p.getStatus()))
            .toList();
        return ResponseEntity.ok(activeParams);
    }
}
