package com.butik.ProductService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 30/03/2022
 */
@RestController
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private Environment environment;

    @PostMapping
    public String createProduct(){
        return "HTTP POST request handled";
    }

    @GetMapping
    public String getProduct(){
        return "HTTP GET request handled: " + environment.getProperty("local.server.port");
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
