package com.gmail.ramawthar.priyash.categories.services;

import java.net.URI;
import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import com.gmail.ramawthar.priyash.categories.model.Categories;

public interface CategoriesService {
	public List<Categories> getAllCategories();
	public URI createCategory(Categories categories);
	

    String processCSVFile(MultipartFile file, String refresh);
	
	public void removeCategory(Long id);
	public String allocateParent(Categories categories);
	public Categories getCategory(String category);
	public List<Categories> getAllSiblings(String category);
	public List<Categories> getAllChildren(String parent);
	public List<Categories> getAllUncategorised();

    
	//to do
	public String getPath(String category, String tranType);
	public String getPathUI(String category, String tranType);
	
}
