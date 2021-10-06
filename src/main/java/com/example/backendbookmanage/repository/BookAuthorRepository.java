package com.example.backendbookmanage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backendbookmanage.entity.BookAuthorEntity;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthorEntity, Long> {
	
	List<BookAuthorEntity> findByBookId(Long bookId);
	

}
