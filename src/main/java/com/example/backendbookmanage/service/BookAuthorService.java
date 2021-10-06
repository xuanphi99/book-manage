package com.example.backendbookmanage.service;

import com.example.backendbookmanage.model.dto.AuthorDto;

import java.util.List;

public interface BookAuthorService {
	List<AuthorDto> getAllAuthorOfBook(Long bookId);

}
