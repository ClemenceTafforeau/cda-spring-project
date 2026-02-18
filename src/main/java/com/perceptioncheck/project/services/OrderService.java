package com.perceptioncheck.project.services;

import com.perceptioncheck.project.exceptions.StockException;
import com.perceptioncheck.project.models.Order;
import com.perceptioncheck.project.models.OrderProduct;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private List<Order> orders = new ArrayList<>();
    private ProductService productService;

    // Getters

    public ProductService getProductService() {
        return productService;
    }

    // Setters

    public void setProductService(ProductService pProductService) {
        productService = pProductService;
    }

    // Other methods

    /**
     * Returns a list of all orders
     * @return a list of all orders
     */
    public List<Order> getAll() {
        return orders;
    }

    /**
     * Returns an order after setting its status to "ONGOING" and adding it
     * to the list of orders
     * @return an order
     */
    public Order create(Order pOrder) {
        pOrder.setStatus("ONGOING");
        orders.add(pOrder);
        return pOrder;
    }

    /**
     * Sets an order's status to "PAID" as long as the corresponding product's stock
     * is sufficient. If it isn't, the method fails silently
     */
    public void pay(Order pOrder) {
        if (pOrder.getStatus().equals("PAID")) {
            return;
        }

        List<OrderProduct> orderProducts = pOrder.getOrderProducts();
        try {
            orderProducts.forEach(orderProduct -> {
                productService.delete(orderProduct.getProduct(), orderProduct.getQuantity());
            });
            pOrder.setStatus("PAID");
        } catch (StockException e) {
            System.out.println(e.getMessage());
        }
    };
}
