package com.banca.cuenta.movimientos.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @class_name Account.java
 * @class_description This class represents an account entity.
 * @author
 * @create_date 2025-08-20
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    @ManyToOne
    @JoinColumn(name = "type_parameter_id", nullable = false)
    private Parameter typeParameter;
    private Double initialBalance;
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public Parameter getTypeParameter() { return typeParameter; }
    public void setTypeParameter(Parameter typeParameter) { this.typeParameter = typeParameter; }
    public Double getInitialBalance() { return initialBalance; }
    public void setInitialBalance(Double initialBalance) { this.initialBalance = initialBalance; }
    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }
}
