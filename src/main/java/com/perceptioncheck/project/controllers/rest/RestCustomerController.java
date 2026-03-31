package com.perceptioncheck.project.controllers.rest;

import com.perceptioncheck.project.dto.CustomerDTO;
import com.perceptioncheck.project.dto.PasswordUpdateDTO;
import com.perceptioncheck.project.exceptions.InvalidOldPasswordException;
import com.perceptioncheck.project.models.Customer;
import com.perceptioncheck.project.services.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/password/update")
    public String updatePassword(
            HttpServletRequest request,
            @ModelAttribute PasswordUpdateDTO updatePasswordForm
    ) {
        try {
            CustomerDTO customer = customerService.findByEmail(updatePasswordForm.getUsername());

             if (!customerService.checkIfValidFormerPassword(customer, updatePasswordForm.getFormerPassword())) {
                throw new InvalidOldPasswordException("Invalid credentials");
             }

            boolean success = customerService.changeCustomerPassword(customer, updatePasswordForm.getPassword());
            if (success) {
                return "login";
            } else {
                return "update-password";
            }
        } catch (NullPointerException e) {
            return "update-password";
        }
    }

    @GetMapping("")
    private List<CustomerDTO> index() {
        return customerService.getAll();
    }
}
