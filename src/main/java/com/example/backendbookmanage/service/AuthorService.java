package com.example.backendbookmanage.service;

import java.util.List;
import java.util.Map;

import com.example.backendbookmanage.entity.AuthorEnity;

public interface AuthorService {
    Map<Long, String> findAllAuthor();

	List<AuthorEnity> getAllAuthor();
}
