package com.perceptioncheck.project.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pc_roles")
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String role;

    // Getters

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
