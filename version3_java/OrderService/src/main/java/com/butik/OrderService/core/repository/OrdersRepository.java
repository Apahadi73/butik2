package com.butik.OrderService.core.repository;

import com.butik.OrderService.core.models.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author amirpahadi
 * @brief
 * @version 1.0
 * @since 04/04/2022
 */
public interface OrdersRepository extends JpaRepository<OrderEntity, String> {
    OrderEntity findByOrderId(String orderId);
}
