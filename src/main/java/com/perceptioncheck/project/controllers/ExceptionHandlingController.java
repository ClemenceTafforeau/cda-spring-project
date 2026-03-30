package com.perceptioncheck.project.controllers;

import com.perceptioncheck.project.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleResourceNotFoundException(ResourceNotFoundException e) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        mav.addObject("status", HttpStatus.NOT_FOUND.value());
        mav.addObject("message", e.getMessage());
        mav.setStatus(HttpStatus.NOT_FOUND);
        return mav;
    }
}

