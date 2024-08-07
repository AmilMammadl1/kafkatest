package com.alas.task1.controller;

import com.alas.task1.dto.product.ProductRequestDto;
import com.alas.task1.model.Product;
import com.alas.task1.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProductController {
    private ProductService service;

    @PostMapping("/createProduct")
    public Product createProduct(@RequestBody ProductRequestDto productRequsetDto) {
        return service.createProduct(productRequsetDto);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
         service.delete(id);
    }

}
