package com.butik.ProductService.core.repository;

import com.butik.ProductService.core.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author amirpahadi
 * @brief
 * @version 1.0
 * @since 04/04/2022
 */
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    ProductEntity findByProductId(String productId);
    ProductEntity findByProductIdOrTitle(String productId, String title);
}
