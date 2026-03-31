package com.perceptioncheck.project.services;

import com.perceptioncheck.project.dto.CustomerDTO;
import com.perceptioncheck.project.dto.OrderDTO;
import com.perceptioncheck.project.dto.OrderProductDTO;
import com.perceptioncheck.project.exceptions.StockException;
import com.perceptioncheck.project.models.Order;
import com.perceptioncheck.project.models.OrderProduct;
import com.perceptioncheck.project.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("orders")
public class OrderService {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderRepository orderRepository;

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
    public List<OrderDTO> getAll() {
        return orderRepository.findAll()
            .stream()
            .map(order -> {
                CustomerDTO customer = new CustomerDTO(order.getCustomer().getId(), order.getCustomer().getEmail(), order.getCustomer().getPassword(), order.getCustomer().getRoles());
                List<OrderProductDTO> orderProducts = order.getOrderProducts()
                        .stream()
                        .map(orderProduct -> new OrderProductDTO(orderProduct.getProduct(), orderProduct.getQuantity())).toList();
                return new OrderDTO(order.getId(), order.getDate(), order.getStatus(), customer, orderProducts);
            })
            .collect(Collectors.toList());
    }

    /**
     * Returns an order after setting its status to "ONGOING" and adding it
     * to the list of orders
     * @return an order
     */
    public Order create(Order pOrder) {
        pOrder.setStatus("PENDING");
        orderRepository.save(pOrder);
        return pOrder;
    }

    /**
     * Sets an order's status to "PAID" as long as the corresponding product's stock
     * is sufficient. If it isn't, the method fails silently
     */
    public void pay(Order pOrder) {
        if (pOrder.getStatus().equals("COMPLETED")) {
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
