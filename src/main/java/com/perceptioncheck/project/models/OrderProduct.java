package com.perceptioncheck.project.models;

public class OrderProduct {

    private Order order;
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
