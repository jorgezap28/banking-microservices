package com.banca.cuenta.movimientos.dto;

import java.time.LocalDate;

public class CustomerReportDTO {
    private String name;
    private Double initialBalance;
    private LocalDate date;
    private String parameterValue;
    private String transactionTypeAmount;
    private Double balance;
    private String accountNumber;
    private String customerStatus;
    private String movementType;

    public CustomerReportDTO() {}

    public CustomerReportDTO(String name, Double initialBalance, LocalDate date, String parameterValue, String transactionTypeAmount, Double balance, String accountNumber, String customerStatus, String movementType) {
        this.name = name;
        this.initialBalance = initialBalance;
        this.date = date;
        this.parameterValue = parameterValue;
        this.transactionTypeAmount = transactionTypeAmount;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.customerStatus = customerStatus;
        this.movementType = movementType;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getInitialBalance() { return initialBalance; }
    public void setInitialBalance(Double initialBalance) { this.initialBalance = initialBalance; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getParameterValue() { return parameterValue; }
    public void setParameterValue(String parameterValue) { this.parameterValue = parameterValue; }
    public String getTransactionTypeAmount() { return transactionTypeAmount; }
    public void setTransactionTypeAmount(String transactionTypeAmount) { this.transactionTypeAmount = transactionTypeAmount; }
    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public String getCustomerStatus() { return customerStatus; }
    public void setCustomerStatus(String customerStatus) { this.customerStatus = customerStatus; }
    public String getMovementType() { return movementType; }
    public void setMovementType(String movementType) { this.movementType = movementType; }
}
