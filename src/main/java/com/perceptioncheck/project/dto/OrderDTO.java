package com.perceptioncheck.project.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {

    private Long id;
    private LocalDateTime date;
    private String status;
    private CustomerDTO customer;
    private List<OrderProductDTO> orderProducts;

    public OrderDTO(Long id, LocalDateTime date, String status, CustomerDTO customer, List<OrderProductDTO> orderProducts) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.customer = customer;
        this.orderProducts = orderProducts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public List<OrderProductDTO> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProductDTO> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
