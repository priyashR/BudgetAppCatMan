package com.gmail.ramawthar.priyash.categories.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	@Override
	public URI createCategory(Categories categories){
		categories.set_id(ObjectId.get());
		Categories newCategory = repository.save(categories);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCategory.get_id()).toUri();
	    return location;
	}
	
	@Override
	public void removeCategory(ObjectId id){
		repository.delete(repository.findBy_id(id));
	}

	@Override
	public Categories getCategory(String category){
		return repository.findBycategory(category);
	}
	

	@Override
	public Categories allocateParent(String category){
		return null;
	}
	
	//to do
	@Override
	public List<Categories> getAllSiblings(String category){
		Categories categories = repository.findBycategory(category);
		return repository.findByparent(categories.parent);
	}
	
	@Override
	public List<Categories> getAllChildren(String parent){
		return repository.findByparent(parent);
	}
	@Override
	public List<Categories> getAllUncategorised(){
		List<Categories> unCatExp = repository.findByparent("expenseUNCAT");
		List<Categories> unCatInc = repository.findByparent("incomeUNCAT");
		List<Categories> unCatFull = new ArrayList<Categories>(unCatExp);
		unCatFull.addAll(unCatInc);
		return unCatFull;
	}
	@Override
	public String getPath(String category){
		return null;
	}
}
