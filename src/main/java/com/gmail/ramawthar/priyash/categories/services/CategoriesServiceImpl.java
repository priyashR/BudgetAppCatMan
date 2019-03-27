package com.gmail.ramawthar.priyash.categories.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.ramawthar.priyash.categories.dao.CategoriesRepository;
import com.gmail.ramawthar.priyash.categories.model.Categories;

@Service
public class CategoriesServiceImpl implements CategoriesService {
 
	@Autowired
	private CategoriesRepository repository;
	
	@Override
	public List<Categories> getAllCategories() {
		return repository.findAll();
	}

}
