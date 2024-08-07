package com.alas.task1.service;

import com.alas.task1.dto.carts.AddProductsToShoppingCartsRequestDto;
import com.alas.task1.dto.carts.ShoppingCartsRequestDto;
import com.alas.task1.dto.carts.ShoppingCartsResponseDto;
import com.alas.task1.model.ShoppingCarts;

import java.util.List;


public interface ShoppingCartsService {
    ShoppingCarts addProduct(AddProductsToShoppingCartsRequestDto dto);

    ShoppingCarts createCart(ShoppingCartsRequestDto cartsRequestDto);

    void removeProductFromCart(Integer cartId,List<Integer> productId);
    ShoppingCartsResponseDto getShoppingCartById(Integer cartId);
}
