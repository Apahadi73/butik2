package com.butik.ProductService.command.rest;

import com.butik.ProductService.command.service.ProductCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.butik.ProductService.core.models.CreateProductRestModel;

import javax.validation.Valid;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 30/03/2022
 */
@RestController
@RequestMapping("/products")
public class ProductsCommandController {

    private ProductCreateService productService;

    @Autowired
    public ProductsCommandController(ProductCreateService productService){
        // field variables
        this.productService = productService;
    }

    @PostMapping
    public String createProduct(@Valid @RequestBody CreateProductRestModel createProductRestModel){
        return productService.createProduct(createProductRestModel);
    }

//    @PutMapping
//    public String updateProduct(){
//        return "HTTP PUT request handled";
//    }
//
//    @DeleteMapping
//    public String deleteProduct(){
//        return "HTTP DELETE request handled";
//    }
}
