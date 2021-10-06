package com.example.backendbookmanage.service;

import java.util.List;
import java.util.Map;

import com.example.backendbookmanage.entity.PublisherEntity;

public interface PublisherService {
    Map<Long, String> findAllPublisher();

	List<PublisherEntity> getAllPublishers();
}
