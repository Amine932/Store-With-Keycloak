package com.example.productservice.Web;


import com.example.productservice.DTOs.ProductRequestDTO;
import com.example.productservice.DTOs.ProductResponseDTO;
import com.example.productservice.Service.ProductServiceInterface;
import com.example.productservice.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@org.springframework.web.bind.annotation.RestController

@RequestMapping("/api")

@RefreshScope
public class RestController {

    @Autowired
    ProductServiceInterface productServiceInterface;

    @Autowired
    GlobalConfig globalConfig;


    @GetMapping("/GlobalConfig")
    public GlobalConfig globalConfig()
    {
        return globalConfig;
    }

    @GetMapping("/Products")
    @PreAuthorize("hasRole('admin')")

    public List<ProductResponseDTO> listProduct(SecurityContextHolder auth)
    {
        System.out.println(auth.getClass());

        return productServiceInterface.listProducts();
    }

    @PostMapping("/Products")
    public void addProduct(@RequestBody ProductRequestDTO productRequestDTO)
    {
        productServiceInterface.addProduct(productRequestDTO);
    }

    @PutMapping("/Products/{id}")

    public void updateProduct(@PathVariable Integer id,@RequestBody ProductRequestDTO productRequestDTO)
    {
        productServiceInterface.updateProduct(id,productRequestDTO);
    }


    @GetMapping("/Products/{id}")
    @PreAuthorize("hasRole('user')")
    public ProductResponseDTO productById(@PathVariable Integer id)
    {
        return  productServiceInterface.productById(id);
    }

    @DeleteMapping("/Products/{id}")
    public void deleteProduct(@PathVariable Integer id)
    {
        productServiceInterface.deleteProduct(id);
    }


}
