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

import java.time.LocalDate;

/**
 * @class_name Movement.java
 * @class_description This class represents a movement entity.
 * @author
 * @create_date 2025-08-20
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "transaction_type_parameter_id", nullable = false)
    private Parameter transactionTypeParameter;
    private Double value;
    private Double balance;
    private String transactionTypeAmount;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public Parameter getTransactionTypeParameter() { return transactionTypeParameter; }
    public void setTransactionTypeParameter(Parameter transactionTypeParameter) { this.transactionTypeParameter = transactionTypeParameter; }
    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }
    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }
    public String getTransactionTypeAmount() { return transactionTypeAmount; }
    public void setTransactionTypeAmount(String transactionTypeAmount) { this.transactionTypeAmount = transactionTypeAmount; }
    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }
}
