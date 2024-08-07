package com.alas.task1.controller;

import com.alas.task1.dto.productDetails.ProductDetailsRequestDto;
import com.alas.task1.dto.productDetails.ProductDetailsResponseDto;
import com.alas.task1.service.DetailsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProductDetailsController {
    private DetailsService service;

    @PostMapping("/createProductDetails")
    public ProductDetailsResponseDto createProductDetails(@RequestBody ProductDetailsRequestDto detailsRequestDto
            ,@RequestParam Integer productId) {
        return service.createProductDetails(detailsRequestDto,productId);
    }
    }
