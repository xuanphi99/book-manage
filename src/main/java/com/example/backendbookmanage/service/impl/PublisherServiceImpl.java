package com.example.backendbookmanage.service.impl;

import com.example.backendbookmanage.entity.CategoryEntity;
import com.example.backendbookmanage.entity.PublisherEntity;
import com.example.backendbookmanage.repository.CategoryRepository;
import com.example.backendbookmanage.repository.PublisherRepository;
import com.example.backendbookmanage.service.CategoryService;
import com.example.backendbookmanage.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Override
    public Map<Long, String> findAllPublisher() {
        List<PublisherEntity> categoryEnityList = publisherRepository.findAll();

        Map<Long, String> mapPublisher = new HashMap<>();
        if (!CollectionUtils.isEmpty(categoryEnityList)){
            categoryEnityList.forEach(item ->  {
                if (!mapPublisher.containsKey(item.getId())){
                    mapPublisher.put(item.getId(),item.getName());
                }
            } );

        }

        return mapPublisher;
    }

	@Override
	public List<PublisherEntity> getAllPublishers() {
		// TODO Auto-generated method stub
		return publisherRepository.findAll();
	}
}
