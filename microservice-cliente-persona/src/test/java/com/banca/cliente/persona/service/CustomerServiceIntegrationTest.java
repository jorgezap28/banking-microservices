package com.banca.cliente.persona.service;

import com.banca.cliente.persona.domain.Customer;
import com.banca.cliente.persona.domain.Person;
import com.banca.cliente.persona.dto.CustomerReportDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class CustomerServiceIntegrationTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void testCreateAndGetCustomer() {
        Person person = new Person();
        person.setName("Juan Perez");
        // ...otros campos necesarios...
        Customer customer = new Customer();
    customer.setStatus(true);
        Customer created = customerService.createCustomer(customer, 1L);
        assertNotNull(created.getId());
        Optional<Customer> found = customerService.getCustomerById(created.getId());
        assertTrue(found.isPresent());
    assertEquals(true, found.get().getStatus());
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        assertNotNull(customers);
    }

    @Test
    public void testUpdateCustomer() {
        Customer customer = new Customer();
    customer.setStatus(true);
        Customer created = customerService.createCustomer(customer, 1L);
    created.setStatus(false);
        Customer updated = customerService.updateCustomer(created.getId(), created, 1L);
    assertEquals(false, updated.getStatus());
    }

    @Test
    public void testDeleteCustomer() {
        Customer customer = new Customer();
    customer.setStatus(true);
        Customer created = customerService.createCustomer(customer, 1L);
        customerService.deleteCustomer(created.getId());
        Optional<Customer> found = customerService.getCustomerById(created.getId());
        assertFalse(found.isPresent());
    }

    @Test
    public void testGetCustomerReport() {
        List<CustomerReportDTO> report = customerService.getCustomerReport(1L, "2025-01-01", "2025-12-31");
        assertNotNull(report);
    }
}
