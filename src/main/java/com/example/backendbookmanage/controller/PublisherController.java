package com.example.backendbookmanage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backendbookmanage.config.ResponseFactoryCustom;
import com.example.backendbookmanage.constant.Constants;
import com.example.backendbookmanage.entity.PublisherEntity;
import com.example.backendbookmanage.model.custom.GeneralResponseCustom;
import com.example.backendbookmanage.model.dto.BookDto;
import com.example.backendbookmanage.model.dto.CategoryDto;
import com.example.backendbookmanage.model.response.BookResponseDto;
import com.example.backendbookmanage.service.AuthorService;
import com.example.backendbookmanage.service.CategoryService;
import com.example.backendbookmanage.service.PublisherService;
import com.example.backendbookmanage.service.impl.AmazonClientImpl;
import com.example.backendbookmanage.service.impl.BookServiceImpl;
import com.example.backendbookmanage.validator.FileValidator;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(path = Constants.APP_PATH_BASE_PUBLIC,produces = "application/json")
@Api(value = "Category API")
public class PublisherController {
	
	private final PublisherService publisherService ;
    private final ResponseFactoryCustom factoryCustom;

	
    @GetMapping(path = Constants.PathMapping.API_PUBLISHER)
    public ResponseEntity<GeneralResponseCustom< List<PublisherEntity>>> getAllAuthor(){
    	List<PublisherEntity> publisherEntities = publisherService.getAllPublishers();
        return factoryCustom.success(publisherEntities);

    }

	

}
