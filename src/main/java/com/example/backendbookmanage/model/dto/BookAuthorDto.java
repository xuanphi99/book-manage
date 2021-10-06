package com.example.backendbookmanage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookAuthorDto {
	private Long id;
	private Long bookId;
	private Long authorId;

}
