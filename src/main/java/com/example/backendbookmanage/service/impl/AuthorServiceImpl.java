package com.example.backendbookmanage.service.impl;

import com.example.backendbookmanage.entity.AuthorEnity;
import com.example.backendbookmanage.repository.AuthorRepository;
import com.example.backendbookmanage.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Map<Long, String> findAllAuthor() {
        List<AuthorEnity> authorEnityList = authorRepository.findAll();

        Map<Long, String> mapAuthor = new HashMap<>();
        if (!CollectionUtils.isEmpty(authorEnityList)){
            authorEnityList.forEach(item ->  {
                if (!mapAuthor.containsKey(item.getAuthorId())){
                    mapAuthor.put(item.getAuthorId(),item.getName());
                }
            } );

        }

        return mapAuthor;
    }

	@Override
	public List<AuthorEnity> getAllAuthor() {
		// TODO Auto-generated method stub
		return authorRepository.findAll();
	}
}
