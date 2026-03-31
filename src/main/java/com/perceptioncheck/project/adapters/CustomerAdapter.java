package com.perceptioncheck.project.adapters;

import com.perceptioncheck.project.dto.CustomerDTO;
import com.perceptioncheck.project.models.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerAdapter {

    public CustomerAdapter() {
        super();
    }

    public CustomerDTO toDto(Customer pCustomer) {
        CustomerDTO customer = new CustomerDTO();

        customer.setId(pCustomer.getId());
        customer.setEmail(pCustomer.getEmail());
        customer.setPassword(pCustomer.getPassword());
        customer.setRoles(pCustomer.getRoles());

        return customer;
    }

    public Customer toModel(CustomerDTO pCustomer) {
        Customer customer = new Customer();

        customer.setId(pCustomer.getId());
        customer.setEmail(pCustomer.getEmail());
        customer.setPassword(pCustomer.getPassword());
        customer.setRoles(pCustomer.getRoles());

        return customer;
    }
}
