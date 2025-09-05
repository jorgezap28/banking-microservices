package com.banca.cliente.persona.domain;

// Removed javax.persistence imports
import jakarta.persistence.*;

@Entity
@Table(name = "type_parameter")
public class TypeParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String value;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
