package com.example.backendbookmanage.service;

import java.util.List;
import java.util.Map;

import com.example.backendbookmanage.entity.CategoryEntity;
import com.example.backendbookmanage.model.dto.CategoryDto;

public interface CategoryService {
    Map<Long, String> findAllCategory();

	List<CategoryEntity> getAllCategory();

}
