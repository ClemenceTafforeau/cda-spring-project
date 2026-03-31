package com.perceptioncheck.project.repositories;

import com.perceptioncheck.project.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.roles WHERE c.email = :email")
    Optional<Customer> findByEmailWithRoles(String email);
}
