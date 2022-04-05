package com.butik.ProductService.query.rest;

import com.butik.ProductService.core.models.CreateProductRestModel;
import com.butik.ProductService.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 05/04/2022
 */
@RestController
@RequestMapping("/products")
public class ProductsQueryController {

    // field variables
    private Environment environment;
    private ProductService productService;

    @Autowired
    public ProductsQueryController(Environment environment, ProductService productService){
        this.environment = environment;
        this.productService = productService;
    }
    @GetMapping
    public List<CreateProductRestModel> getProducts(){
        return "HTTP GET request handled: " + environment.getProperty("local.server.port");
    }


}
