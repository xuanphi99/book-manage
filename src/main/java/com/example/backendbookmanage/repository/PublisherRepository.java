package com.example.backendbookmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backendbookmanage.entity.AuthorEnity;
import com.example.backendbookmanage.entity.BookEntity;
import com.example.backendbookmanage.entity.CategoryEntity;
import com.example.backendbookmanage.entity.PublisherEntity;

@Repository
public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {

}
