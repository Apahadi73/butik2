package com.butik.ProductService.query.handler.query;

import com.butik.ProductService.core.models.ProductEntity;
import com.butik.ProductService.core.repository.ProductRepository;
import com.butik.ProductService.query.models.FindProductsQuery;
import com.butik.ProductService.query.models.ProductRestModel;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 05/04/2022
 */
@Component
@Slf4j
public class ProductsQueryHandlerImpl implements ProductsQueryHandler {

    private final ProductRepository productRepository;

    public ProductsQueryHandlerImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    /**
     * @param findProductsQuery - an instance of FindProductsQuery event
     * @return list of products
     */
    @QueryHandler
    @Override
    public List<ProductRestModel> findProducts(FindProductsQuery findProductsQuery){
        List<ProductRestModel> productList = new ArrayList<>();
        try{
            List<ProductEntity> productEntities = productRepository.findAll();
            productEntities.forEach(productEntity -> {
                ProductRestModel productRestModel = new ProductRestModel();
                BeanUtils.copyProperties(productEntity, productRestModel);
                productList.add(productRestModel);
            });
        }
        catch (Exception e){
            log.error(e.getLocalizedMessage());
        }

        return productList;
    }
}
