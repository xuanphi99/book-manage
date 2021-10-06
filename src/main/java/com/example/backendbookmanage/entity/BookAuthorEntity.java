package com.example.backendbookmanage.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_book_author")
public class BookAuthorEntity {
	
	 	@Id
	    @Column(name = "id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

	    @Column(name = "id_book")
	    private Long bookId;

	    @Column(name = "id_author")
	    private Long authorId;

	

}
