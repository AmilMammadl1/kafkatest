package com.alas.task1.service;

import com.alas.task1.dto.productDetails.ProductDetailsRequestDto;
import com.alas.task1.dto.productDetails.ProductDetailsResponseDto;

public interface DetailsService {
    ProductDetailsResponseDto createProductDetails(ProductDetailsRequestDto detailsRequestDto, Integer productId);
    void delete(Integer id);
}
