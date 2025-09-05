package com.banca.cuenta.movimientos.dto;

public class MovementReportDTO {
    private String date;
    private String customerName;
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private Boolean status;
    private Double movementValue;
    private String movementType;
    private Double balance;
    private Double available;

    public MovementReportDTO() {}

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public Double getInitialBalance() { return initialBalance; }
    public void setInitialBalance(Double initialBalance) { this.initialBalance = initialBalance; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    public Double getMovementValue() { return movementValue; }
    public void setMovementValue(Double movementValue) { this.movementValue = movementValue; }

    public String getMovementType() { return movementType; }
    public void setMovementType(String movementType) { this.movementType = movementType; }

    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }

    public Double getAvailable() { return available; }
    public void setAvailable(Double available) { this.available = available; }
}
