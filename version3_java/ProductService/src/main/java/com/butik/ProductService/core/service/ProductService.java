package com.butik.ProductService.core.service;

import com.butik.ProductService.core.models.CreateProductRestModel;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 31/03/2022
 */
public interface ProductService {
    String createProduct(CreateProductRestModel createProductRestModel);
}
