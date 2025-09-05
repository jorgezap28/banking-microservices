package com.banca.cliente.persona.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.banca.cliente.persona.domain.Parameter;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double initialBalance;

    // ...existing code...

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @ManyToOne
    @JoinColumn(name = "parameter_id")
    private Parameter parameter;

    // Puedes agregar más relaciones y campos según tu modelo

    // Getters y setters
    public Parameter getParameter() { return parameter; }
    public void setParameter(Parameter parameter) { this.parameter = parameter; }
    // ...existing code...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getInitialBalance() { return initialBalance; }
    public void setInitialBalance(Double initialBalance) { this.initialBalance = initialBalance; }

    // ...existing code...

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
}
