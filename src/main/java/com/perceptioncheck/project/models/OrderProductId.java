package com.perceptioncheck.project.models;

import java.io.Serializable;

public class OrderProductId implements Serializable {
    private Order order;
    private Product product;

    public OrderProductId(Order order, Product product) {
        super();
    }
}