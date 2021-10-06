package com.example.backendbookmanage.model.response;

import com.example.backendbookmanage.model.custom.Paging;
import com.example.backendbookmanage.model.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {
    private List<BookDto> bookDtoList;
    private Paging paging;
}
