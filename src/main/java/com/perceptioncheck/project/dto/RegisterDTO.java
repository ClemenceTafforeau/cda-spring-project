package com.perceptioncheck.project.dto;

import com.perceptioncheck.project.validation.ValidEmail;
import com.perceptioncheck.project.validation.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@ValidPassword
public class RegisterDTO {

    @ValidEmail
    private String username;

    @NotBlank
    @Size(min=6)
    private String password;

    @NotBlank
    private String confirmPassword;

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public String getConfirmPassword() { return confirmPassword; }

    public void setUsername(String pUsername) { username = pUsername; }

    public void setPassword(String pPassword) { password = pPassword; }

    public void setConfirmPassword(String pConfirmPassword) { confirmPassword = pConfirmPassword; }
}
