package com.example.backendbookmanage.controller;

import com.example.backendbookmanage.config.ResponseFactoryCustom;
import com.example.backendbookmanage.constant.Constants;
import com.example.backendbookmanage.model.dto.BookDto;
import com.example.backendbookmanage.model.custom.GeneralResponseCustom;
import com.example.backendbookmanage.model.response.BookResponseDto;
import com.example.backendbookmanage.service.impl.AmazonClientImpl;
import com.example.backendbookmanage.service.impl.BookServiceImpl;
import com.example.backendbookmanage.utils.CommonUtil;
import com.example.backendbookmanage.validator.FileValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.util.List;

import javax.persistence.Id;
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = Constants.APP_PATH_BASE_PUBLIC,produces = "application/json")
@Api(value = "Book API")
public class BookController {


    private final AmazonClientImpl amazonClientImpl;
    private final FileValidator fileValidator;
    private final BookServiceImpl bookService;
    private final ResponseFactoryCustom factoryCustom;
    @ApiOperation(value = "Xem danh sách BOOK", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Thành công"),
            @ApiResponse(code = 401, message = "Chưa xác thực"),
            @ApiResponse(code = 403, message = "Truy cập bị cấm"),
            @ApiResponse(code = 404, message = "Không tìm thấy")
    })
    
// GET ALL BOOK pagination and sort by name book    
    @GetMapping(path = Constants.PathMapping.API_BOOK)
    public ResponseEntity<GeneralResponseCustom<List<BookDto>>> getAllBook(
            @RequestParam Long categoryId,
            @RequestParam(required = false, defaultValue = "0") Integer currentPage,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize
            ){
        BookResponseDto bookResponseDto = bookService.getAllBookByCategory(categoryId,currentPage,pageSize);
        return factoryCustom.success(bookResponseDto.getBookDtoList(),bookResponseDto.getPaging());

    }

    @GetMapping(path = Constants.PathMapping.API_BOOK+"/admin")
    public ResponseEntity<GeneralResponseCustom<List<BookDto>>> getAll(){
        List<BookDto> bookResponseDto = bookService.getAllBook();
        return factoryCustom.success(bookResponseDto);

    }
    
    @GetMapping(path = Constants.PathMapping.API_BOOK+"/{id}")
    public ResponseEntity<GeneralResponseCustom<BookDto>> getDetailBook(@PathVariable  Long id){
      return  factoryCustom.success( bookService.getDeatilBook(id));
    }

    @PostMapping(value = Constants.PathMapping.API_BOOK,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GeneralResponseCustom<BookDto>> addBook(@RequestPart(value = "file", required = false) MultipartFile file , @RequestPart String jsonData ) throws IOException {

        fileValidator.checkFileUpload(file);
        //upload file : url ; fileName
        String fileInfo = amazonClientImpl.uploadFile(file);
        ObjectMapper objectMapper = new ObjectMapper();
        BookDto bookDto = objectMapper.readValue(jsonData , BookDto.class);
        System.out.println(bookDto.toString());
        bookDto.setImgName(fileInfo.split("\\;")[1]);
        bookDto.setImgUrl(fileInfo.split("\\;")[0]);
        return factoryCustom.success( bookService.addBook(bookDto));
    }

    @PutMapping(path = Constants.PathMapping.API_BOOK+"/{id}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GeneralResponseCustom<BookDto>> updateBook(
    		@RequestPart(value = "file", required = false) MultipartFile file , 
    		@PathVariable Long id ,
    		@RequestPart String jsonData ) throws IOException {
           ObjectMapper objectMapper = new ObjectMapper();
        BookDto bookDto = objectMapper.readValue(jsonData , BookDto.class);
    	if (!CommonUtil.isNullOrEmpty(file) ) {
    		fileValidator.checkFileUpload(file); 
		//upload file : url ; fileName
        String fileInfo = amazonClientImpl.uploadFile(file);
        	bookDto.setImgName(fileInfo.split("\\;")[1]);
        	bookDto.setImgUrl(fileInfo.split("\\;")[0]);
		}
    	
        return factoryCustom.success( bookService.updateBook(bookDto,id));
    }
    
    
    @DeleteMapping(path = Constants.PathMapping.API_BOOK + "/{id}")
    public ResponseEntity<GeneralResponseCustom<Boolean>> removeDepartment ( @PathVariable Long id){

        return factoryCustom.success(bookService.removeBook(id));
    }


}
