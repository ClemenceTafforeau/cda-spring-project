package com.perceptioncheck.project.services;

import com.perceptioncheck.project.exceptions.ResourceNotFoundException;
import com.perceptioncheck.project.models.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("customers")
public class CustomerService {

    private final List<Customer> customers = new ArrayList<>();

    /**
     * Returns a list of all customers
     * @return a list of all customers
     */
    public List<Customer> getAll() {
        return customers;
    }

    public Customer findById(Long pId) throws ResourceNotFoundException {
        return customers.stream()
                .filter(customer -> Objects.equals(customer.getId(), pId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("The corresponding customer could not be found."));
    }

    public Customer save(Customer pCustomer) {
        customers.add(pCustomer);
        return pCustomer;
    }
}
