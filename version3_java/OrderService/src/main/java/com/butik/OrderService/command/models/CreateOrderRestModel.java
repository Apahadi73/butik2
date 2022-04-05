package com.butik.OrderService.command.models;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 05/04/2022
 */
@Data
public class CreateOrderRestModel {
    @NotBlank(message = "productId is missing")
    private String productId;

    @NotBlank(message = "productId is missing")
    private String addressId;

    @Max(value = 5,message = "Price cannot be greater than 5")
    @Min(value = 1,message = "Price cannot be lower than 1")
    private Integer quantity;
}