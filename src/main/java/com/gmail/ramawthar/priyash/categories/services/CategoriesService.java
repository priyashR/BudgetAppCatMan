package com.gmail.ramawthar.priyash.categories.services;

import java.net.URI;
import java.util.List;

import org.bson.types.ObjectId;
import com.gmail.ramawthar.priyash.categories.model.Categories;

public interface CategoriesService {
	public List<Categories> getAllCategories();
	public URI createCategory(Categories categories);
	public void removeCategory(ObjectId id);
	public Categories getCategory(String category);
	public List<Categories> getAllSiblings(String category);
	public List<Categories> getAllChildren(String parent);
	
	//to do
	public List<Categories> getAllUncategorised();
	public String getPath(String category);
	public Categories allocateParent(String category);
	
}
