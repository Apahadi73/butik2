package com.butik.ProductService.core.models;

import java.math.BigDecimal;

import lombok.Data;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class CreateProductRestModel {
	@NotBlank(message = "Product title is a required field")
	private String title;

	@Min(value = 1,message = "Price cannot be lower than 1")
	private BigDecimal price;

	@Max(value = 5,message = "Price cannot be greater than 5")
	@Min(value = 1,message = "Price cannot be lower than 1")
	private Integer quantity;
}
