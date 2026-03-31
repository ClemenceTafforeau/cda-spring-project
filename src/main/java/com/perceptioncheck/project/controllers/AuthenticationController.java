package com.perceptioncheck.project.controllers;

import com.perceptioncheck.project.dto.PasswordUpdateDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.Map;

@Controller
public class AuthenticationController {

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
}
