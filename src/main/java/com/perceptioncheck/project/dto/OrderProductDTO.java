package com.perceptioncheck.project.dto;

import com.perceptioncheck.project.models.Product;

public class OrderProductDTO {

    private Product product;
    private int quantity;

    public OrderProductDTO(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
