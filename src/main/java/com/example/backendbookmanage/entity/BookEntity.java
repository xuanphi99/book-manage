package com.example.backendbookmanage.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_book")
public class BookEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;


    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "img_name")
    private String imgName;

    @Column(name = "publisher_id")
    private Long publisherId;


}
