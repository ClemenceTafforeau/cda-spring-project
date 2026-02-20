package com.perceptioncheck.project;

import com.perceptioncheck.project.models.Product;
import com.perceptioncheck.project.services.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.*")
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ProjectApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(ProductService productService) {
		return args -> {
			Product product1 = new Product(1L, "Lapis lazuli set (10)", "A set of 10 lapis lazuli dice", 16.99, "dice_1.webp",8);
			Product product2 = new Product(2L, "Blood speckles set (12)", "A set of 12 dice, with blood speckle patterns", 18.99, "dice_2.webp",6);
			Product product3 = new Product(3L, "Gunslinger set (8)", "A set of 8 Gunslinger-themed dice", 15.99, "dice_3.webp",5);
			Product product4 = new Product(4L, "Pyromancer set (8)", "A set of 8 Pyromancer-themed dice", 15.99, "dice_4.webp",9);
			Product product6 = new Product(5L, "Cursed Touch set (10)", "A set of 10 dice from the Cursed Touch collection", 18.99, "dice_5.webp",12);
			Product product5 = new Product(6L, "Blood speckles set (8)", "A set of 8 dice, with blood speckle patterns", 15.99, "dice_6.webp",11);

			productService.save(product1);
			productService.save(product2);
			productService.save(product3);
			productService.save(product4);
			productService.save(product5);
			productService.save(product6);
		};
	}
}
