package com.example.backendbookmanage.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backendbookmanage.entity.BookEntity;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Page<BookEntity> findAllByCategoryId(Long categoryId, Pageable pageable);
}
