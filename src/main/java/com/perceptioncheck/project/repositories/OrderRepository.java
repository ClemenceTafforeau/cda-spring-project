package com.perceptioncheck.project.repositories;

import com.perceptioncheck.project.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByStatusAndCustomerId(String pStatus, Long pCustomerId);
}
