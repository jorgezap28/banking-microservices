package com.banca.cliente.persona.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "parameter")
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String value;
    private String status;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parameter parent;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Parameter getParent() { return parent; }
    public void setParent(Parameter parent) { this.parent = parent; }
}
