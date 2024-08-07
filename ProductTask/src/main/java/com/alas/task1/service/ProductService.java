package com.alas.task1.service;

import com.alas.task1.dto.product.ProductRequestDto;
import com.alas.task1.model.Product;

public interface ProductService {
    Product createProduct(ProductRequestDto productRequsetDto);
    void delete(Integer id);
}
