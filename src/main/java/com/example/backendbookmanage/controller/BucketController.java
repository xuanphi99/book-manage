package com.example.backendbookmanage.controller;

import com.example.backendbookmanage.service.impl.AmazonClientImpl;
import com.example.backendbookmanage.validator.FileValidator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/storage/")
public class BucketController {

    private final AmazonClientImpl amazonClientImpl;
    private final FileValidator fileValidator;

    @SneakyThrows
    @PostMapping( "/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file
      ) {
        fileValidator.checkFileUpload(file);
       return this.amazonClientImpl.uploadFile(file);
    }
}
