package com.butik.ProductService.query.service;

import com.butik.ProductService.query.models.FindProductsQuery;
import com.butik.ProductService.query.models.ProductRestModel;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 31/03/2022
 */

@Service
@Slf4j
public class ProductQueryServiceImpl implements ProductQueryService {

    // field variables
    private final QueryGateway queryGateway;

    @Autowired
    public ProductQueryServiceImpl(QueryGateway queryGateway){
        this.queryGateway = queryGateway;
    }


    /**
     *
     * @return list of products
     */
    @Override
    public List<ProductRestModel> findProducts() {
        List<ProductRestModel> products = null;
        try{
            FindProductsQuery findProductsQuery = new FindProductsQuery();
            products= this.queryGateway.query(findProductsQuery,
                    ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();
        }
        catch (Exception e){
            log.error(e.getLocalizedMessage());
        }

        return products;
    }
}
