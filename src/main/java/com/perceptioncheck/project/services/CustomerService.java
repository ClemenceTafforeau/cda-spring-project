package com.perceptioncheck.project.services;

import com.perceptioncheck.project.adapters.CustomerAdapter;
import com.perceptioncheck.project.dto.CustomerDTO;
import com.perceptioncheck.project.dto.RegisterDTO;
import com.perceptioncheck.project.exceptions.ResourceNotFoundException;
import com.perceptioncheck.project.models.Customer;
import com.perceptioncheck.project.repositories.CustomerRepository;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("customers")
public class CustomerService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerAdapter customerAdapter;

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    /**
     * Returns a list of all customers
     * @return a list of all customers
     */
    public List<CustomerDTO> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> new CustomerDTO(customer.getId(), customer.getEmail(), customer.getPassword(), customer.getRoles()))
                .collect(Collectors.toList());
    }

    public CustomerDTO findByEmail(String email) throws ResourceNotFoundException {
        Optional<Customer> customer = customerRepository.findByEmailWithRoles(email);
        if (customer.isPresent()) {
            return customerAdapter.toDto(customer.get());
        } else {
            throw new ResourceNotFoundException("Customer with email " + email + " could not be found");
        }
    }

    public CustomerDTO findById(Long id) throws ResourceNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customerAdapter.toDto(customer.get());
        } else {
            throw new ResourceNotFoundException("The corresponding customer could not be found.");
        }
    }

    public Customer save(Customer pCustomer) {
        customerRepository.save(pCustomer);
        return pCustomer;
    }

    public boolean checkIfValidFormerPassword(CustomerDTO pCustomer, String password) {
        Optional<Customer> customer = customerRepository.findById(pCustomer.getId());
        if (customer.isPresent()) {
            String result = customer.get().getPassword();
            return passwordEncoder.matches(password, result);
        } else {
            return false;
        }
    }

    public boolean changeCustomerPassword(CustomerDTO pCustomer, String password) {
        Optional<Customer> customer = customerRepository.findById(pCustomer.getId());
        if (customer.isPresent()) {
            String encodedPassword = passwordEncoder.encode(password);
            customer.get().setPassword(encodedPassword);
            customerRepository.save(customer.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean registerNewCustomer(RegisterDTO pRegisterDTO) {
        Optional<Customer> customer = customerRepository.findByEmail(pRegisterDTO.getUsername());
        if (customer.isPresent()) {
            return false;
        } else {
            Customer newCustomer = new Customer(pRegisterDTO.getUsername(), passwordEncoder.encode(pRegisterDTO.getPassword()));
            try {
                customerRepository.save(newCustomer);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            CustomerDTO customer = findByEmail(username);
            return findByEmail(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
