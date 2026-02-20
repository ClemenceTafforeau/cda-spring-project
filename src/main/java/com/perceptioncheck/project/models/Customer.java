package com.perceptioncheck.project.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pc_customers")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;



    public Customer() {
        super();
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters

    public void setId(Long pId) {
        id = pId;
    }

    public void setEmail(String pEmail) {
        email = pEmail;
    }

    public void setPassword(String pPassword) {
        password = pPassword;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
