package com.alas.task1.service.Impl;

import com.alas.task1.dto.category.CategoryRequestDto;
import com.alas.task1.dto.category.CategoryResponseDto;
import com.alas.task1.mapper.CategoryMapper;
import com.alas.task1.model.Category;
import com.alas.task1.repository.CategoryRepository;
import com.alas.task1.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CategoryServiceImpl implements CategoryService {

    CategoryMapper mapper;
    CategoryRepository repository;

//
//    public CategoryServiceImpl(CategoryMapper categoryMapper,CategoryRepository categoryRepository){
//        this.mapper = categoryMapper;
//        this.repository = categoryRepository;
//    }


    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto requestDto) {



        Category category = mapper.mapCategoryRequestToEntity(requestDto);

         return        mapper.mapEntityToCategoryResponse(repository.save(category));
    }
}
