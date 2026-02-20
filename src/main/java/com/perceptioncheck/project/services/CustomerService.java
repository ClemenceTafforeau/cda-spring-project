package com.perceptioncheck.project.services;

import com.perceptioncheck.project.exceptions.ResourceNotFoundException;
import com.perceptioncheck.project.models.Customer;
import com.perceptioncheck.project.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customers")
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Returns a list of all customers
     * @return a list of all customers
     */
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long pId) throws ResourceNotFoundException {
        return customerRepository.findById(pId).orElseThrow(() -> new ResourceNotFoundException("The corresponding customer could not be found."));
    }

    public Customer save(Customer pCustomer) {
        customerRepository.save(pCustomer);
        return pCustomer;
    }
}
