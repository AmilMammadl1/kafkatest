package com.alas.task1.controller;

import com.alas.task1.dto.category.CategoryRequestDto;
import com.alas.task1.dto.category.CategoryResponseDto;
import com.alas.task1.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CategoryController {
    private CategoryService service;

    @PostMapping("/createCategory")
    public CategoryResponseDto createCategory(@RequestBody CategoryRequestDto requestDto) {
        return service.createCategory(requestDto);
    }
    }
