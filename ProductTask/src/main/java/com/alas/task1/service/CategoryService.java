package com.alas.task1.service;

import com.alas.task1.dto.category.CategoryRequestDto;
import com.alas.task1.dto.category.CategoryResponseDto;

public interface CategoryService {
    CategoryResponseDto createCategory(CategoryRequestDto requestDto);
}
