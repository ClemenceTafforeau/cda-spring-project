package com.perceptioncheck.project.controllers.rest;

import com.perceptioncheck.project.models.Order;
import com.perceptioncheck.project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products")
public class RestProductController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    private String addProductToCart(@RequestParam Long productId, Model model) {
        try {
            int quantity =  1;
            Order order = orderService.addProductToCart(productId, quantity);

            return order != null ? "cart" : "products";
        } catch (Exception e) {
            return "products";
        }
    }
}
