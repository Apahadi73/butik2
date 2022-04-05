package com.butik.ProductService.query.handler.query;

import com.butik.ProductService.query.models.FindProductsQuery;
import com.butik.ProductService.query.models.ProductRestModel;
import org.axonframework.queryhandling.QueryHandler;

import java.util.List;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 05/04/2022
 */
public interface ProductsQueryHandler {
    public List<ProductRestModel> findProducts(FindProductsQuery findProductsQuery);
}
