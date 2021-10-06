package com.example.backendbookmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backendbookmanage.entity.AuthorEnity;
import com.example.backendbookmanage.entity.BookEntity;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEnity, Long> {

}
