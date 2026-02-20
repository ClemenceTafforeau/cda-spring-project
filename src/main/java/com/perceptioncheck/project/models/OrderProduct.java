package com.perceptioncheck.project.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pc_order_products")
@IdClass(OrderProductId.class)
public class OrderProduct {

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;

    public OrderProduct() {
        super();
    }

    // Getters

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setters

    public void setOrder(Order pOrder) {
        order = pOrder;
    }

    public void setProduct(Product pProduct) {
        product = pProduct;
    }

    public void setQuantity(int pQuantity) {
        quantity = pQuantity;
    }

    // Other methods

    public Double getTotalPrice() {
        return getProduct().getPrice() * (double) getQuantity();
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
