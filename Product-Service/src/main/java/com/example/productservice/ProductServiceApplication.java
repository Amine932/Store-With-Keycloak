package com.example.productservice;

import com.example.productservice.Entities.Product;
import com.example.productservice.Repositories.ProductRepository;
import com.example.productservice.config.GlobalConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableConfigurationProperties({GlobalConfig.class})

public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}



    @Bean
	CommandLineRunner start (ProductRepository productRepository)
	{
		return args -> {
			for (int i = 0;i<6;i++)
			{
				Product product = Product.builder()
						.brand(UUID.randomUUID().toString().substring(0,6))
						.desc(UUID.randomUUID().toString().substring(0,6))
						.price(Math.round((new Random()).nextDouble(30000) * 100.0) / 100.0)
						.quantity((new Random()).nextInt(50))
						.build();

				productRepository.save(product);
			}
		};
	}
}
