package com.butik.ProductService.core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 05/04/2022
 * brief: ProductLookUpEntity instance is used for set data consistency in command product service
 */
@Entity
@Table(name="productlookup")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductLookUpEntity implements Serializable {
    private static final long serialVersionUID = 1669293150219020249L;

    @Id
    private String productId;

    @Column(unique = true)
    private String title;
}

