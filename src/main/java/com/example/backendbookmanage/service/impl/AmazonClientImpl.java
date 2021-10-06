package com.example.backendbookmanage.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.backendbookmanage.exception.BusinessException;
import com.example.backendbookmanage.model.local.MessageCode;
import com.example.backendbookmanage.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
@Slf4j
public class AmazonClientImpl {
    private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = new AmazonS3Client(credentials);
    }



    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String uploadFile(MultipartFile multipartFile) {

        String fileUrl = "";
        String fileName = CommonUtil.generateFileName(multipartFile);

        try {
            File file = CommonUtil.convertMultiPartToFile(multipartFile);

            if (file.length()/1024 ==0){ // upload file max size == 1MB
                throw new BusinessException(MessageCode.DOGOO_01, MessageCode.DOGOO_01);
            }

            fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
           uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            log.info("Upload file thất bại {}",e.getMessage());
            throw new BusinessException(MessageCode.DOGOO_01, MessageCode.DOGOO_01);
        }
        return fileUrl+";"+fileName;
    }

}
