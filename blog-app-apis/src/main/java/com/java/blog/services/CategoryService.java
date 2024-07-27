package com.java.blog.services;

import java.util.List;

import com.java.blog.payloads.CategoryDto;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    void deleteCategory(Integer categoryId);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    CategoryDto getCategory(Integer categoryId);

    List<CategoryDto> getCategories();
}
