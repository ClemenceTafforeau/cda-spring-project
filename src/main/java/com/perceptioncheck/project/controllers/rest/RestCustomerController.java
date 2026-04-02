package com.perceptioncheck.project.controllers.rest;

import com.perceptioncheck.project.dto.CustomerDTO;
import com.perceptioncheck.project.models.Customer;
import com.perceptioncheck.project.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("principal.isJeanPierre()")
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        try {
            Customer customer = (Customer)
            SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return "profile";
        } catch (NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    @GetMapping("")
    private List<CustomerDTO> index() {
        return customerService.getAll();
    }
}
