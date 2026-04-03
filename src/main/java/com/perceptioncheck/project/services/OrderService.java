package com.perceptioncheck.project.services;

import com.perceptioncheck.project.adapters.CustomerAdapter;
import com.perceptioncheck.project.dto.CustomerDTO;
import com.perceptioncheck.project.dto.OrderDTO;
import com.perceptioncheck.project.dto.OrderProductDTO;
import com.perceptioncheck.project.exceptions.NoAuthenticatedUserException;
import com.perceptioncheck.project.exceptions.StockException;
import com.perceptioncheck.project.models.Customer;
import com.perceptioncheck.project.models.Order;
import com.perceptioncheck.project.models.OrderProduct;
import com.perceptioncheck.project.models.Product;
import com.perceptioncheck.project.repositories.OrderProductRepository;
import com.perceptioncheck.project.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("orders")
public class OrderService {

    @Autowired
    private ProductService productService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private CustomerAdapter customerAdapter;

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

    public Optional<Order> getOngoingOrder(Long pCustomerId) {
        return orderRepository.findByStatusAndCustomerId("PLACED", pCustomerId);
    }

    public Order addProductToCart(Long pProductId, int pQuantity) {
        String authenticatedCustomerIdentifier = authenticationService.getAuthenticatedCustomer();

        if (authenticatedCustomerIdentifier.isEmpty()) {
            throw new NoAuthenticatedUserException("No authenticated user");
        }

        Product product = productService.findById(pProductId);
        boolean isProductAvailable = productService.isAvailable(product, pQuantity);

        if (!isProductAvailable) {
            throw new StockException("Product is unavailable");
        }

        Customer customer = customerAdapter.toModel(customerService.findByEmail(authenticatedCustomerIdentifier));
        Optional<Order> placedOrder = getOngoingOrder(customer.getId());
        Order order;

        if (placedOrder.isPresent()) {
            order = placedOrder.get();
        } else {
            order = new Order();
            order.setCustomer(customer);
            order.setDate(LocalDateTime.now());
            order.setStatus(String.valueOf(Order.Status.PLACED));
            order.setOrderProducts(new ArrayList<>());
        }

        order.addProduct(product, pQuantity);

        for (OrderProduct orderProduct : order.getOrderProducts()) {
            System.out.println(order);
            orderProduct.setOrder(order);
            System.out.println("orderproductotbesaved" + orderProduct);
            orderProductRepository.save(orderProduct);
        }

        orderRepository.save(order);

        return order;
    };
}
