package com.perceptioncheck.project.dto;

public class PasswordUpdateDTO {

    private String username;
    private String password;
    private String formerPassword;
    private String message;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFormerPassword() {
        return formerPassword;
    }

    public void setFormerPassword(String formerPassword) {
        this.formerPassword = formerPassword;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
