package com.example.backendbookmanage.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Long categoryId;
    private String imgUrl;
    private String imgName;
    private Long publisherId;
    private String categoryName;
    private String publisherName;
    private List<AuthorDto> authorDtoList;

 
}
