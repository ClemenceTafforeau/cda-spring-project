package com.perceptioncheck.project.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Order {

    private Long id;
    private Customer customer;
    private LocalDate date;
    private String status;
    private List<OrderProduct> orderProducts;

    public enum Status {
        PLACED(1),
        PAID(2),
        CANCELLED(3);

        private final int value;

        Status(int value) {
            this.value = value;
        }
    }

    public Order() {
        super();
    }

    // Getters

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    // Setters

    public void setId(Long pId) {
        id = pId;
    }

    public void setCustomer(Customer pCustomer) {
        customer = pCustomer;
    };

    public void setDate(LocalDate pDate) {
        date = pDate;
    }

    public void setStatus(String pStatus) {
        status = pStatus;
    };

    public void setOrderProducts(List<OrderProduct> pOrderProducts) {
        orderProducts = pOrderProducts;
    }

    /**
     * Calculating the order's total price
     * @return the total price as a double
     */
    public Double getTotalPrice() {
        return orderProducts.stream().mapToDouble(OrderProduct::getTotalPrice).sum();
    };

    /**
     * Calculating the amount of product types in the order
     * @return the amount of product types
     */
    public int getProductTypesAmount() {
        return orderProducts.size();
    };

    /**
     * Calculating the amount of products in the order
     * @return the amount of products
     */
    public int getTotalProductAmount() {
        return orderProducts.stream().mapToInt(OrderProduct::getQuantity).sum();
    };

    /**
     * Adding an OrderProduct object to a list of OrderProducts
     */
    public void addProduct(Product pProduct, int pQuantity) {
        OrderProduct orderProduct = new OrderProduct();
        OrderProduct matchInList = getProductIfInList(orderProduct);

        if (Objects.isNull(matchInList)) {
            orderProduct.setOrder(this);
            orderProduct.setProduct(pProduct);
            orderProduct.setQuantity(pQuantity);
            orderProducts.add(orderProduct);
        } else {
            matchInList.setQuantity(matchInList.getQuantity() + pQuantity);
        }
    };

    public OrderProduct getProductIfInList(OrderProduct pOrderProduct) {
        return orderProducts.stream().filter(orderProduct -> orderProduct.equals(pOrderProduct)).findFirst().orElse(null);
    }

    // Other methods

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", orderProducts=" + orderProducts +
                '}';
    }
}
