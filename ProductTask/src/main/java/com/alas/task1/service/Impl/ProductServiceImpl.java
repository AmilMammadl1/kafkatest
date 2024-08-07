package com.alas.task1.service.Impl;

import com.alas.task1.dto.product.ProductRequestDto;
import com.alas.task1.mapper.ProductMapper;
import com.alas.task1.model.Category;
import com.alas.task1.model.Product;
import com.alas.task1.model.ProductDetails;
import com.alas.task1.repository.CategoryRepository;
import com.alas.task1.repository.DetailsRepository;
import com.alas.task1.repository.ProductRepository;
import com.alas.task1.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {
    ProductMapper mapper;
    ProductRepository repository;
    DetailsRepository productDetailsRepository;
    CategoryRepository categoryRepository;

    @Override
    public Product createProduct(ProductRequestDto dto) {
        Product product = new Product();
        product.setName(dto.getName());

        Optional<ProductDetails> productDetails = productDetailsRepository.findById(dto.getProductDetailsId());
        if(productDetails.isPresent()){
            product.setProductDetails(productDetails.get());
        }
        else {
            ProductDetails productDetails1 =  new ProductDetails();
            productDetails1.setColor("red");
            productDetails1.setImage_url("test details");
            productDetails1.setProduct(product);

            product.setProductDetails(productDetails1);
        }


        Optional<Category> category = categoryRepository.findById(dto.getCategoryId());
        if(category.isPresent()){
            product.setCategory( category.get());
        }
        else {
            Category category1 = new Category();
            category1.setName("test category");

            product.setCategory(category1);
        }

        Product createdProduct = repository.save(product);


        return createdProduct;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
