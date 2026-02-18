package com.perceptioncheck.project;

import com.perceptioncheck.project.models.Customer;
import com.perceptioncheck.project.models.Order;
import com.perceptioncheck.project.models.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;

// @SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) throws Exception {
		// SpringApplication.run(ProjectApplication.class, args);

		Product product1 = new Product();
		Product product2 = new Product();
		Product product3 = new Product();
		Customer customer1 = new Customer();
		Order order1 = new Order();

		product1.setId(1L);
		product2.setId(2L);
		product3.setId(3L);

		product1.setName("D20");
		product2.setName("D4");
		product3.setName("D6");

		product1.setDescription("Le fameux D20");
		product2.setDescription("Le fameux D4");
		product3.setDescription("Le fameux D6");

		product1.setPrice(2.00);
		product2.setPrice(1.20);
		product3.setPrice(1.60);

		product1.setImage("d20.png");
		product2.setImage("d4.png");
		product3.setImage("d6.png");

		product1.setQuantity(3);
		product2.setQuantity(6);
		product3.setQuantity(7);

		customer1.setId(1L);
		customer1.setEmail("coucou@dnd.fr");
		customer1.setPassword("admin123");

		order1.setId(1L);
		order1.setCustomer(customer1);
		order1.setDate(LocalDate.now());
		order1.setStatus("PAID");
		order1.setOrderProducts(new ArrayList<>());

		order1.addProduct(product1, 2);

		System.out.println(order1);
	}

}
