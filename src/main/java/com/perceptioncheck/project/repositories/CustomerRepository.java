package com.perceptioncheck.project.repositories;

import com.perceptioncheck.project.dto.CustomerDTO;
import com.perceptioncheck.project.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<CustomerDTO> findByEmail(String email);
}
