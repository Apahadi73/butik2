package com.butik.ProductService.query.service;

import com.butik.ProductService.query.models.ProductRestModel;

import java.util.List;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 31/03/2022
 */
public interface ProductQueryService {
    public List<ProductRestModel> findProducts();
}
