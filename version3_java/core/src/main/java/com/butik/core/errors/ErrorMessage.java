package com.butik.ProductService.core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * brief: custom error message class
 * @author amirpahadi
 * @version 1.0
 * @since 05/04/2022
 */
@Data
@AllArgsConstructor
public class ErrorMessage implements Serializable {
    private final Date timestamp;
    private final String message;
}
