package com.perceptioncheck.project.models;

import java.time.LocalDate;
import java.util.List;

public class Order {

    private Long id;
    private Customer customer;
    private LocalDate date;
    private String status;
    private List<OrderProduct> orderProducts;

    public Order() {
        super();
    }
}
