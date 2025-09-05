package com.banca.cliente.persona.controller;

import com.banca.cliente.persona.domain.Customer;
import com.banca.cliente.persona.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @class_name CustomerController.java
 * @class_description REST controller for managing customers.
 * @create_date 2025-08-20
 */
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/report")
    public ResponseEntity<List<com.banca.cliente.persona.dto.CustomerReportDTO>> getCustomerReport(
            @RequestParam Long idCliente,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {
        List<com.banca.cliente.persona.dto.CustomerReportDTO> report = customerService.getCustomerReport(idCliente, fechaInicio, fechaFin);
        return ResponseEntity.ok(report);
    }

    @PostMapping
    public ResponseEntity<com.banca.cliente.persona.dto.CustomerDTO> createCustomer(@RequestBody Customer customer, @RequestParam Long personId) {
        Customer created = customerService.createCustomer(customer, personId);
        String name = created.getPerson() != null ? created.getPerson().getName() : null;
        com.banca.cliente.persona.dto.CustomerDTO dto = new com.banca.cliente.persona.dto.CustomerDTO(
            created.getId(),
            name,
            created.getEmail()
        );
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.banca.cliente.persona.dto.CustomerDTO> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(customer -> {
                    String name = customer.getPerson() != null ? customer.getPerson().getName() : null;
                    com.banca.cliente.persona.dto.CustomerDTO dto = new com.banca.cliente.persona.dto.CustomerDTO(
                        customer.getId(),
                        name,
                        customer.getEmail()
                    );
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<com.banca.cliente.persona.dto.CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody Customer customer, @RequestParam Long personId) {
        Customer updated = customerService.updateCustomer(id, customer, personId);
        String name = updated.getPerson() != null ? updated.getPerson().getName() : null;
        com.banca.cliente.persona.dto.CustomerDTO dto = new com.banca.cliente.persona.dto.CustomerDTO(
            updated.getId(),
            name,
            updated.getEmail()
        );
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
