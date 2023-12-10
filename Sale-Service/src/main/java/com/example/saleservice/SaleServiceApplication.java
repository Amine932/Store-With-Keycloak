package com.example.saleservice;

import com.example.saleservice.Entities.Sale;
import com.example.saleservice.Repositories.SaleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
@EnableFeignClients

public class SaleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaleServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(SaleRepository saleRepository)
    {
        return args ->
        {
            for (int i=0;i<3;i++)
            {
                Sale sale = Sale.builder()
                        .month(UUID.randomUUID().toString().substring(0,6))
                        .year(UUID.randomUUID().toString().substring(0,6))
                        .build();

                saleRepository.save(sale);
            }
            Sale sale1 = Sale.builder()
                    .month(UUID.randomUUID().toString().substring(0,6))
                    .year(UUID.randomUUID().toString().substring(0,6))
                    .idProduct(1)
                    .idClient(1)

                    .build();
            saleRepository.save(sale1);

            Sale sale2 = Sale.builder()
                    .month(UUID.randomUUID().toString().substring(0,6))
                    .year(UUID.randomUUID().toString().substring(0,6))
                    .idProduct(2)
                    .idClient(3)

                    .build();
            saleRepository.save(sale2);


        };

    }



}
