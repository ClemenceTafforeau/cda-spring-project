package com.perceptioncheck.project;

import com.perceptioncheck.project.models.Customer;
import com.perceptioncheck.project.models.Order;
import com.perceptioncheck.project.models.OrderProduct;
import com.perceptioncheck.project.models.Product;
import com.perceptioncheck.project.services.OrderService;
import com.perceptioncheck.project.services.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProjectApplication.class, args);
		ApplicationContext context = new ClassPathXmlApplicationContext("services.xml");

		ProductService productService = context.getBean("products", ProductService.class);
		OrderService orderService = context.getBean("orders", OrderService.class);

		Product product1 = new Product();
		product1.setId(1L);
		product1.setName("D20");
		product1.setDescription("Desc");
		product1.setQuantity(8);
		product1.setImage("d20.png");
		product1.setPrice(2.50);

		productService.save(product1);

		productService.getAll().forEach(System.out::println);

		Customer customer1 = new Customer();
		customer1.setId(2L);
		customer1.setEmail("coucou@customer.com");
		customer1.setPassword("123customer");

		// Création d’une instance d’une nouvelle order -> order
		Order order1 = new Order();
		order1.setId(1L);
		order1.setDate(LocalDate.now());
		order1.setStatus(null);
		order1.setCustomer(customer1);
		order1.setOrderProducts(new ArrayList<OrderProduct>());

		order1.addProduct(product1, 6);
		System.out.println(order1);

		orderService.create(order1);
		System.out.println(order1);

		orderService.pay(order1);
		System.out.println(order1);
	}

}
