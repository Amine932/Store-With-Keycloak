package com.example.clientservice.products;


import com.example.clientservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductFeignController
{

    @GetMapping ("/api/Products")
    public List<Product> findAllProduct();


    @GetMapping("/api/Products/{id}")
    public Product findProductById(@PathVariable Integer id);

}
