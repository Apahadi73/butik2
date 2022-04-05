package com.butik.ProductService.core.models;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 31/03/2022
 */
@Data
public class ProductCreatedEvent {
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
