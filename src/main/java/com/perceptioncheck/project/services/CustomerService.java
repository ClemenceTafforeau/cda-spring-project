package com.perceptioncheck.project.services;

import com.perceptioncheck.project.dto.CustomerDTO;
import com.perceptioncheck.project.exceptions.ResourceNotFoundException;
import com.perceptioncheck.project.models.Customer;
import com.perceptioncheck.project.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("customers")
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Returns a list of all customers
     * @return a list of all customers
     */
    public List<CustomerDTO> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> new CustomerDTO(customer.getId(), customer.getEmail()))
                .collect(Collectors.toList());
    }

    public CustomerDTO findByEmail(String email) throws ResourceNotFoundException {
        return customerRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with email " + email + " could not be found"));
    }

    public Customer findById(Long id) throws ResourceNotFoundException {
        return customerRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The corresponding customer could not be found."));
    }

    public Customer save(Customer pCustomer) {
        customerRepository.save(pCustomer);
        return pCustomer;
    }
}
