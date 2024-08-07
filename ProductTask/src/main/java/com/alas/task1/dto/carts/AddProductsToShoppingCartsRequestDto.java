package com.alas.task1.dto.carts;

import lombok.Data;

import java.util.List;

@Data
public class AddProductsToShoppingCartsRequestDto {
    //private List<Product> products;
    private Integer cardId;
    private List<Integer> productIds;


}
