package com.butik.ProductService.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 30/03/2022
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {

    @PostMapping
    public String createProduct(){
        return "HTTP POST request handled";
    }

    @GetMapping
    public String getProduct(){
        return "HTTP GET request handled";
    }

    @PutMapping
    public String updateProduct(){
        return "HTTP PUT request handled";
    }

    @DeleteMapping
    public String deleteProduct(){
        return "HTTP DELETE request handled";
    }
}
