package com.banca.cliente.persona.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.DiscriminatorColumn;

/**
 * @class_name Person.java
 * @class_description This class represents a person entity.
 * @author
 * @create_date 2025-08-20
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")

public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phone;
    private java.time.LocalDate registrationDate;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public java.time.LocalDate getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(java.time.LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
