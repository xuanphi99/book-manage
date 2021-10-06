package com.example.backendbookmanage.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.backendbookmanage.entity.BookAuthorEntity;
import com.example.backendbookmanage.mapper.CommonMapper;
import com.example.backendbookmanage.model.dto.AuthorDto;
import com.example.backendbookmanage.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.backendbookmanage.repository.BookAuthorRepository;
import com.example.backendbookmanage.service.BookAuthorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookAuthorServiceImpl implements BookAuthorService {
	
	private final BookAuthorRepository bookAuthorRepository;
	private final AuthorRepository authorRepository;
	private final ModelMapper mapper;

	@Override
	public List<AuthorDto> getAllAuthorOfBook(Long bookId) {
		List<BookAuthorEntity> bookAuthorEntityList = bookAuthorRepository.findByBookId(bookId);
		List<AuthorDto> authorDtoList = new ArrayList<>();
		for ( BookAuthorEntity item : bookAuthorEntityList) {
			System.out.println(authorRepository.findById(item.getAuthorId()).toString());
		authorDtoList.add(
				 mapper.map(authorRepository.findById(item.getAuthorId()).get(), AuthorDto.class)
		);
		}

		return authorDtoList;
	}

}
