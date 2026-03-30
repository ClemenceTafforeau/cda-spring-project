package com.perceptioncheck.project.controllers.rest;

import com.perceptioncheck.project.dto.CustomerDTO;
import com.perceptioncheck.project.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/customers")
public class RestCustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(params = "email")
    private CustomerDTO getCustomerByEmail(@RequestParam String email) {
        return customerService.findByEmail(email);
    }

    @GetMapping("")
    private List<CustomerDTO> index() {
        return customerService.getAll();
    }
}
