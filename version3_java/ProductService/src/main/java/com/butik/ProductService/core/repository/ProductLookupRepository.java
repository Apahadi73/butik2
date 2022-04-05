package com.butik.ProductService.core.repository;

import com.butik.ProductService.core.models.ProductEntity;
import com.butik.ProductService.core.models.ProductLookUpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author amirpahadi
 * @brief
 * @version 1.0
 * @since 04/04/2022
 */
public interface ProductLookupRepository extends JpaRepository<ProductLookUpEntity, String> {
    ProductLookUpEntity findByProductIdOrTitle(String productId, String title);
}
