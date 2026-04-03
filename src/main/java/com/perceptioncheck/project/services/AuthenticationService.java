package com.perceptioncheck.project.services;

import com.perceptioncheck.project.exceptions.NoAuthenticatedUserException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("authentication")
public class AuthenticationService {

    public String getAuthenticatedCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken) && authentication != null) {
            return authentication.getName();
        } else {
            return "";
        }
    }
}
