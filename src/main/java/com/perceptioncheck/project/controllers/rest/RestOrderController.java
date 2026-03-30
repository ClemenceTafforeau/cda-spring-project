package com.perceptioncheck.project.controllers.rest;

import com.perceptioncheck.project.dto.OrderDTO;
import com.perceptioncheck.project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class RestOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("")
    private List<OrderDTO> index() {
        return orderService.getAll();
    }
}
