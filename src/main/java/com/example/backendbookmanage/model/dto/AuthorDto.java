package com.example.backendbookmanage.model.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto{
	
    private Long authorId;

    private String name;

    private String address;

    private String phone;

}
