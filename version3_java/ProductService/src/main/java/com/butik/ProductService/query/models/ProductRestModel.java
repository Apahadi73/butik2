package com.butik.ProductService.query.models;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 05/04/2022
 */
@Data
public class ProductRestModel {
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
