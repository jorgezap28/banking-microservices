package com.banca.cliente.persona.service.impl;

import com.banca.cliente.persona.domain.Customer;
import com.banca.cliente.persona.dto.CustomerReportDTO;
import com.banca.cliente.persona.repository.CustomerRepository;
import com.banca.cliente.persona.service.CustomerService;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de CustomerService.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final com.banca.cliente.persona.repository.PersonRepository personRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, com.banca.cliente.persona.repository.PersonRepository personRepository) {
        this.customerRepository = customerRepository;
        this.personRepository = personRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer createCustomer(Customer customer, Long personId) {
        com.banca.cliente.persona.domain.Person person = personRepository.findById(personId)
            .orElseThrow(() -> new IllegalArgumentException("Person not found"));
        customer.setPerson(person);
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
    }
    @Override
    public com.banca.cliente.persona.domain.Person updatePerson(Long personId, com.banca.cliente.persona.domain.Person personData) {
        com.banca.cliente.persona.domain.Person existing = personRepository.findById(personId)
            .orElseThrow(() -> new IllegalArgumentException("Person not found"));
        existing.setAddress(personData.getAddress());
        existing.setPhone(personData.getPhone());
        // Si tienes más campos en Person, actualízalos aquí
        return personRepository.save(existing);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer, Long personId) {
        Customer existing = customerRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        com.banca.cliente.persona.domain.Person person = personRepository.findById(personId)
            .orElseThrow(() -> new IllegalArgumentException("Person not found"));
        existing.setPerson(person);
        existing.setPassword(customer.getPassword());
        existing.setLastLoginDate(customer.getLastLoginDate());
        existing.setLastLoginMethod(customer.getLastLoginMethod());
        existing.setStatus(customer.getStatus());
        existing.setEmail(customer.getEmail());
        existing.setRegistrationDate(customer.getRegistrationDate());
        return customerRepository.save(existing);
    }
    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerReportDTO> getCustomerReport(Long idCliente, String fechaInicio, String fechaFin) {
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);
    String jpql = "SELECT new com.banca.cliente.persona.dto.CustomerReportDTO(" +
        " per.name, act.initialBalance, mov.date, par.value, mov.value, mov.balance) " +
                " FROM Movement mov " +
                " JOIN mov.account act " +
                    " JOIN act.parameter par " +
                " JOIN act.customer person " +
                " JOIN person.person per " +
                " WHERE person.id = :idCliente " +
                " AND mov.date BETWEEN :inicio AND :fin";
        TypedQuery<CustomerReportDTO> query = entityManager.createQuery(jpql, CustomerReportDTO.class);
        query.setParameter("idCliente", idCliente);
        query.setParameter("inicio", inicio);
        query.setParameter("fin", fin);
        return query.getResultList();
    }
}
