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
	public String getPath(String category, String tranType){
		//System.out.println("getPath 1");
		if ((category == null)||(tranType == null)){
			return "Both parameters are required!";
		}
		//System.out.println("getPath 2");
		Categories cat = repository.findBycategory(category);
		if (cat == null){
			Categories categories = new Categories(); 
			categories.setCategory(category);
			categories.setDescription("Transaction");
			String parent = "expenseUNCAT";
			if (tranType.equalsIgnoreCase("I"))
				parent = "incomeUNCAT";
			categories.setParent(parent);
			categories.set_id(ObjectId.get());
			Categories newCategory = repository.save(categories);
			cat = newCategory;
		}
		//System.out.println("getPath 3");
		
		String parentLoop = cat.parent;
		String path = parentLoop+"/"+cat.category;
		//System.out.println("getPath 3a "+ parentLoop + " " + path);
		while (!(parentLoop.equalsIgnoreCase("none"))){
			Categories catLoop = repository.findBycategory(parentLoop);
			path = catLoop.parent+"/"+path;
			parentLoop = catLoop.parent;
		}
		//System.out.println("getPath 4");
		
		return path;
	}

	@Override
	public String allocateParent(Categories categories){
		Categories cat = repository.findBycategory(categories.category);
		if (cat == null){
			return "Category not found - no records updated";
		}
		if (!((cat.parent.equals("expenseUNCAT"))||(cat.parent.equals("incomeUNCAT")))){
			return "Cannot update this parent, must be uncategorised - no records updated";
		}
		if(repository.findBycategory(categories.parent) == null){
			return "Parent category not found - no records updated";
		}
		cat.parent = categories.parent;
		repository.save(cat);
		return "Success!";
	}
}
