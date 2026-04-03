package com.perceptioncheck.project.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pc_orders")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private LocalDateTime date;
    private String status;
    @OneToMany
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

    }

    // Getters

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    };

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    };

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
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
            orderProduct.setProduct(pProduct);
            orderProduct.setQuantity(pQuantity);
            this.orderProducts.add(orderProduct);
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
