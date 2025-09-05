package com.banca.cuenta.movimientos.domain;

import javax.persistence.*;

@Entity
public class Customer {
    @Id
    private Long id;
    private String name;

    private String status;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Person getPerson() { return person; }
    public void setPerson(Person person) { this.person = person; }
}
