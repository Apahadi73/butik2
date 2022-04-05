package com.butik.OrderService.core.models;

import com.butik.OrderService.command.models.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 04/04/2022
 */
@Data
@Entity
@Table(name="orders")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 2669293150219020249L;

    @Id
    @Column(unique = true)
    public String orderId;

    private String productId;
    private String userId;
    private int quantity;
    private String addressId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

}

