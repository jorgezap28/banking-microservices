package com.banca.cuenta.movimientos.domain;

import javax.persistence.*;

@Entity
public class Person {
    @Id
    private Long id;
    private String name;
    // Puedes agregar más campos según tu modelo

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
