package com.example.backendbookmanage.service.impl;

import com.example.backendbookmanage.entity.AuthorEnity;
import com.example.backendbookmanage.entity.CategoryEntity;
import com.example.backendbookmanage.model.dto.CategoryDto;
import com.example.backendbookmanage.repository.AuthorRepository;
import com.example.backendbookmanage.repository.CategoryRepository;
import com.example.backendbookmanage.service.AuthorService;
import com.example.backendbookmanage.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Map<Long, String> findAllCategory() {
        List<CategoryEntity> categoryEnityList = categoryRepository.findAll();

        Map<Long, String> mapCategory = new HashMap<>();
        if (!CollectionUtils.isEmpty(categoryEnityList)){
            categoryEnityList.forEach(item ->  {
                if (!mapCategory.containsKey(item.getId())){
                    mapCategory.put(item.getId(),item.getName());
                }
            } );

        }

        return mapCategory;
    }

	@Override
	public List<CategoryEntity> getAllCategory() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}


}
