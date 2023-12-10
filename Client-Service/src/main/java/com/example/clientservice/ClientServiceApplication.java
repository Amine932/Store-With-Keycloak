package com.example.clientservice;

import com.example.clientservice.Config.ClientConfig;
import com.example.clientservice.Entities.Client;
import com.example.clientservice.Repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties({ClientConfig.class})

public class ClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ClientRepository clientRepository)
	{
		return args ->
		{
			for (int i=0;i<3;i++)
			{
				Client client = Client.builder()
						.name(UUID.randomUUID().toString().substring(0,6))
						.city(UUID.randomUUID().toString().substring(0,6))


						.build();

				clientRepository.save(client);
			}
			Client c1 = Client.builder()
					.name(UUID.randomUUID().toString().substring(0,6))
					.city(UUID.randomUUID().toString().substring(0,6))
					.idProduct(1)

					.build();
			clientRepository.save(c1);

			Client c2 = Client.builder()
					.name(UUID.randomUUID().toString().substring(0,6))
					.city(UUID.randomUUID().toString().substring(0,6))
					.idProduct(2)

					.build();
			clientRepository.save(c2);


		};

	}
}
