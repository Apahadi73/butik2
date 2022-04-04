package com.butik.ProductService.rest;

import com.butik.ProductService.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.butik.ProductService.core.entity.CreateProductRestModel;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 30/03/2022
 */
@RestController
@RequestMapping("/products")
public class ProductsController {

    // field variables
	private Environment environment;
    private ProductService productService;

    @Autowired
    public ProductsController(Environment environment, ProductService productService){
        this.environment = environment;
        this.productService = productService;
    }

    @PostMapping
    public String createProduct(@RequestBody CreateProductRestModel createProductRestModel){
        return productService.createProduct(createProductRestModel);
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
