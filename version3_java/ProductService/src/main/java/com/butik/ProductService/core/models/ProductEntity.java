package com.butik.ProductService.core.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 04/04/2022
 */
@Data
@Entity
@Table(name="products")
public class ProductEntity implements Serializable {
    private static final long serialVersionUID = 2669293150219020249L;

    @Id
    @Column(unique = true)
    private String productId;

    @Column(unique = true)
    private String title;
    private BigDecimal price;
    private Integer quantity;

}
