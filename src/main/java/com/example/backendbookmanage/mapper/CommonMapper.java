package com.example.backendbookmanage.mapper;


import com.example.backendbookmanage.entity.BookEntity;
import com.example.backendbookmanage.model.dto.BookDto;
import com.example.backendbookmanage.model.local.MessageCode;
import com.example.backendbookmanage.exception.BusinessException;
import com.example.backendbookmanage.service.AuthorService;
import com.example.backendbookmanage.service.BookAuthorService;
import com.example.backendbookmanage.service.CategoryService;
import com.example.backendbookmanage.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CommonMapper {

    private final ModelMapper modelMapper;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final PublisherService publisherService;
    private final BookAuthorService bookAuthorService;

    public BookDto convertEntityToBookRes(BookEntity entity , Long idCategory,Long idPublisher){
        Map<Long, String> listCategory = categoryService.findAllCategory();
        Map<Long, String> listPublisher = publisherService.findAllPublisher();
		BookDto res = convertEntitytoDto(entity,BookDto.class);
       res.setAuthorDtoList(bookAuthorService.getAllAuthorOfBook(entity.getId()));
        res.setCategoryName(listCategory.get(idCategory));
        res.setPublisherName(listPublisher.get(idPublisher));

        return res;
    }

    public <T > T convertEntitytoDto(Object obj ,  Class<T> dto) {
        try {
         return modelMapper.map(obj, dto);

        } catch(ClassCastException e) {
            e.printStackTrace();
            throw new BusinessException(MessageCode.DOGOO_99, MessageCode.DOGOO_99);
        }

    }
    public <T > T convertDtotoEntity(Object dto,Class<T> entity  ) {
        try {
         return modelMapper.map(dto, entity);

        } catch(ClassCastException e) {
            e.printStackTrace();
            throw new BusinessException(MessageCode.DOGOO_99, MessageCode.DOGOO_99);
        }

    }
}
