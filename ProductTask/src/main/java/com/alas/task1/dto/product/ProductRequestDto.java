package com.alas.task1.dto.product;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String name;
    private Integer categoryId;
    private Integer productDetailsId;
}
