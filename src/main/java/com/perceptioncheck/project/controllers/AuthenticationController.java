package com.perceptioncheck.project.controllers;

import com.perceptioncheck.project.dto.CustomerDTO;
import com.perceptioncheck.project.dto.PasswordUpdateDTO;
import com.perceptioncheck.project.dto.RegisterDTO;
import com.perceptioncheck.project.exceptions.InvalidOldPasswordException;
import com.perceptioncheck.project.models.Customer;
import com.perceptioncheck.project.services.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.Map;

@Controller
public class AuthenticationController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("password/update")
    public String updatePassword(Model model) {
        model.addAttribute("updatePasswordForm", new PasswordUpdateDTO());
        return "update-password";
    }

    @GetMapping("password/update/success")
    public String updatePasswordSuccess(HttpServletRequest request) {
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            PasswordUpdateDTO passwordUpdateDTO = (PasswordUpdateDTO) inputFlashMap.get("passwordupdatedto");
            return "update-password";
        } else {
            return "update-password";
        }
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("logout")
    public String logout() {
        return "logout";
    }

    @GetMapping("register")
    public String register() { return "register"; }

    @PostMapping("password/update")
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

    @PostMapping("register")
    public String register(@ModelAttribute @Valid RegisterDTO registerDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return "register";
            }

            Customer customer = customerService.registerNewCustomer(registerDTO);

            return customer != null ? "login" : "register";
        } catch (Exception e) {
            return "register";
        }
    }
}
