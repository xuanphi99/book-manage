package com.example.backendbookmanage.service.impl;

import com.example.backendbookmanage.entity.BookAuthorEntity;
import com.example.backendbookmanage.model.custom.Paging;
import com.example.backendbookmanage.model.dto.AuthorDto;
import com.example.backendbookmanage.model.dto.BookAuthorDto;
import com.example.backendbookmanage.model.dto.BookDto;
import com.example.backendbookmanage.model.local.MessageCode;
import com.example.backendbookmanage.entity.BookEntity;
import com.example.backendbookmanage.exception.BusinessException;
import com.example.backendbookmanage.mapper.CommonMapper;
import com.example.backendbookmanage.model.local.ResponseStatusCodeEnum;
import com.example.backendbookmanage.model.response.BookResponseDto;
import com.example.backendbookmanage.repository.BookAuthorRepository;
import com.example.backendbookmanage.repository.BookRepository;
import com.example.backendbookmanage.utils.CommonUtil;
import com.example.backendbookmanage.validator.BookValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl {

    private final BookRepository bookRepository;
    private final CommonMapper mapper;
    private final BookValidator bookValidator;
    private final BookAuthorRepository bookAuthorRepository;

    public BookResponseDto getAllBookByCategory(Long categoryId, Integer currentPage , Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("title"));
        Page<BookEntity> bookEntityPage  = null ;
        if (categoryId == 0) {
        	log.info("API Load toàn bộ sử dụng cho trang admin");
        bookEntityPage   = bookRepository.findAll(pageable) ;
		}
        else  {
        bookEntityPage = bookRepository.findAllByCategoryId(categoryId,pageable) ;
        }
        
        if (!CommonUtil.isNullOrEmpty(bookEntityPage))
        {

            List<BookDto> bookDtoList = bookEntityPage.stream()
                    .map(item -> mapper.convertEntityToBookRes(item,item.getCategoryId(), item.getPublisherId())
                    ).collect(Collectors.toList());
            return BookResponseDto.builder()
                    .bookDtoList(bookDtoList)
                    .paging(Paging
                            .getPagingInfo(Integer.parseInt(String.valueOf(bookEntityPage.getTotalElements())),
                                    pageSize,currentPage))
                    .build();
        }
        throw new BusinessException(MessageCode.DOGOO_01, MessageCode.DOGOO_01);
    }

    public BookDto getDeatilBook(Long id) {
        Optional<BookEntity> bookEntity = Optional.ofNullable(bookRepository.findById(id).orElse(null));
        if (bookEntity.isPresent()){
            return mapper.convertEntityToBookRes(
                    bookEntity.get(),
                    bookEntity.get().getCategoryId(),
                    bookEntity.get().getPublisherId()
                    );
        }
        throw new BusinessException(MessageCode.DOGOO_01, MessageCode.DOGOO_01);

    }

    public boolean removeBook(Long id) {
        try {
            bookRepository.deleteById(id);
            return true;

        }
        catch (Exception e){
            log.info(e.getMessage());
            throw new BusinessException(ResponseStatusCodeEnum.BUSINESS_ERROR.getCode(),
                    MessageCode.DOGOO_04);
        }
    }

    public BookDto addBook(BookDto bookDto) {
      //  bookValidator.checkEmpty(bookDto);
        BookEntity bookEntity = bookRepository.save(mapper.convertDtotoEntity(bookDto,BookEntity.class));
      log.info(bookEntity.toString());
      // save bookAuthor
        List<AuthorDto> authorDtos = bookDto.getAuthorDtoList();
      List<BookAuthorDto> bookAuthorDtos = new ArrayList<>();
        for ( AuthorDto item : authorDtos) {
          BookAuthorDto bookAuthorDto =  BookAuthorDto.builder()
                    .bookId(bookEntity.getId())
                    .authorId(item.getAuthorId())
                    .build();
            BookAuthorEntity bookAuthorEntity = bookAuthorRepository.save(mapper.convertDtotoEntity(bookAuthorDto,BookAuthorEntity.class));
            bookAuthorDtos.add(mapper.convertEntitytoDto(bookAuthorEntity,BookAuthorDto.class));
        }

        return mapper.convertEntityToBookRes(bookEntity , bookEntity.getCategoryId(),bookEntity.getPublisherId());
    }

	public BookDto updateBook(BookDto bookDto, Long id) {
		bookValidator.checkEmpty(bookDto);
		bookDto.setId(id);
		//update book
		BookEntity bookEntity = bookRepository.save(mapper.convertDtotoEntity(bookDto, BookEntity.class));
		
		//update book Author
	    List<AuthorDto> authorDtos = bookDto.getAuthorDtoList();
	      List<BookAuthorDto> bookAuthorDtos = new ArrayList<>();
	   // delete author book prev
	      for (BookAuthorEntity item : bookAuthorRepository.findByBookId(id)) {
			 bookAuthorRepository.deleteById(item.getId());
		}
	    // save Book-Author  
	        for ( AuthorDto item : authorDtos) {
	          BookAuthorDto bookAuthorDto =  BookAuthorDto.builder()
	                    .bookId(bookEntity.getId())
	                    .authorId(item.getAuthorId())
	                    .build();
	            BookAuthorEntity bookAuthorEntity = bookAuthorRepository.save(mapper.convertDtotoEntity(bookAuthorDto,BookAuthorEntity.class));
	            bookAuthorDtos.add(mapper.convertEntitytoDto(bookAuthorEntity,BookAuthorDto.class));
	        }

				 
		return mapper.convertEntityToBookRes(bookEntity , bookEntity.getCategoryId(),bookEntity.getPublisherId());
	}

	public List<BookDto> getAllBook() {
		List<BookEntity> bookEntities = bookRepository.findAll();
		
		List<BookDto> bookDtos = new ArrayList<BookDto>();
		for (BookEntity item : bookEntities) {
			bookDtos.add(mapper.convertEntityToBookRes(item, item.getCategoryId(), item.getPublisherId()));
		}
		
		return bookDtos;
	}
}
