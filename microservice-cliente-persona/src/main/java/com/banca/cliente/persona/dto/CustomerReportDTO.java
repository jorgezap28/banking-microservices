package com.banca.cliente.persona.dto;

import java.time.LocalDate;

public class CustomerReportDTO {
    private String name;
    private Double initialBalance;
    private LocalDate registrationDate;
    private String parameterValue;
    private Double transactionTypeAmount;
    private Double balance;

    public CustomerReportDTO() {}

    public CustomerReportDTO(String name, Double initialBalance, LocalDate registrationDate, String parameterValue, Double transactionTypeAmount, Double balance) {
        this.name = name;
        this.initialBalance = initialBalance;
        this.registrationDate = registrationDate;
        this.parameterValue = parameterValue;
        this.transactionTypeAmount = transactionTypeAmount;
        this.balance = balance;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getInitialBalance() { return initialBalance; }
    public void setInitialBalance(Double initialBalance) { this.initialBalance = initialBalance; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }
    public String getParameterValue() { return parameterValue; }
    public void setParameterValue(String parameterValue) { this.parameterValue = parameterValue; }
    public Double getTransactionTypeAmount() { return transactionTypeAmount; }
    public void setTransactionTypeAmount(Double transactionTypeAmount) { this.transactionTypeAmount = transactionTypeAmount; }
    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }
}
