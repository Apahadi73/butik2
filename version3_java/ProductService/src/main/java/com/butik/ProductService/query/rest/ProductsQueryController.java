package com.butik.ProductService.query.rest;

import com.butik.ProductService.query.models.ProductRestModel;
import com.butik.ProductService.query.service.ProductQueryService;
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
    private final Environment environment;
    private final ProductQueryService productQueryService;

    @Autowired
    public ProductsQueryController(Environment environment, ProductQueryService productQueryService){
        this.environment = environment;
        this.productQueryService = productQueryService;
    }
    
    @GetMapping
    public List<ProductRestModel> getProducts(){
        return productQueryService.findProducts();
    }

    @GetMapping("/version")
    public String getCurrentVersion(){
        return "Version of service: " + environment.getProperty("service.version");
    }


}
