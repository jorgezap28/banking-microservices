package com.banca.cliente.persona.domain;

// Removed javax.persistence imports
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @class_name Customer.java
 * @class_description Entidad Customer como tabla independiente, relacionada con Person.
 */
@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(length = 255)
    private String password;

    private java.time.LocalDate lastLoginDate;

    @Column(length = 255)
    private String lastLoginMethod;

    private Boolean status;

    @Column(length = 255)
    private String email;

    private java.time.LocalDate registrationDate;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Person getPerson() { return person; }
        public void setPerson(Person person) { this.person = person; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public java.time.LocalDate getLastLoginDate() { return lastLoginDate; }
        public void setLastLoginDate(java.time.LocalDate lastLoginDate) { this.lastLoginDate = lastLoginDate; }
        public String getLastLoginMethod() { return lastLoginMethod; }
        public void setLastLoginMethod(String lastLoginMethod) { this.lastLoginMethod = lastLoginMethod; }
        public Boolean getStatus() { return status; }
        public void setStatus(Boolean status) { this.status = status; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public java.time.LocalDate getRegistrationDate() { return registrationDate; }
        public void setRegistrationDate(java.time.LocalDate registrationDate) { this.registrationDate = registrationDate; }
}
