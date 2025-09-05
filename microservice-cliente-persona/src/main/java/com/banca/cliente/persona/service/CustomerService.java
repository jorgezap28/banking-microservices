package com.banca.cliente.persona.service;

import java.util.List;
import java.util.Optional;

import com.banca.cliente.persona.domain.Customer;
import com.banca.cliente.persona.dto.CustomerReportDTO;

/**
 * @class_name CustomerService.java
 * @class_description Service interface for managing customers.
 * @create_date 2025-08-20
 */
public interface CustomerService {

    Customer createCustomer(Customer customer, Long personId);

    Optional<Customer> getCustomerById(Long id);

    List<Customer> getAllCustomers();

    Customer updateCustomer(Long id, Customer customer, Long personId);

    void deleteCustomer(Long id);

    com.banca.cliente.persona.domain.Person updatePerson(Long personId, com.banca.cliente.persona.domain.Person personData);

    List<CustomerReportDTO> getCustomerReport(Long idCliente, String fechaInicio, String fechaFin);
}

