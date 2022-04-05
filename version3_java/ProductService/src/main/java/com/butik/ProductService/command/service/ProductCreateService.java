package com.butik.ProductService.command.service;

import com.butik.ProductService.core.models.CreateProductRestModel;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 31/03/2022
 */
public interface ProductCreateService {
    String createProduct(CreateProductRestModel createProductRestModel) throws Exception;
}
