package com.gmail.ramawthar.priyash.categories.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
		//categories.set_id(ObjectId.get());
		Categories newCategory = repository.save(categories);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCategory.get_id()).toUri();
	    return location;
	}
	
	@Override
	public void removeCategory(Long id){
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
		return repository.findByparent(categories.getParent());
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
		System.out.println("getPath 1");
		if ((category == null)||(tranType == null)){
			return "Both parameters are required!";
		}
		System.out.println("getPath 2 - "+category);
		Categories cat = repository.findBycategory(category);
		if (cat == null){
			Categories categories = new Categories(); 
			categories.setCategory(category);
			categories.setDescription("Transaction");
			String parent = "expenseUNCAT";
			if (tranType.equalsIgnoreCase("I"))
				parent = "incomeUNCAT";
			categories.setParent(parent);
			//categories.set_id(ObjectId.get());
			Categories newCategory = repository.save(categories);
			cat = newCategory;
		}
		//System.out.println("getPath 3");
		
		String parentLoop = cat.getParent();
		String path = parentLoop+"/"+cat.getCategory();
		System.out.println("getPath 3a "+ parentLoop + " " + path);
		while (!(parentLoop.equalsIgnoreCase("none"))){
			Categories catLoop = repository.findBycategory(parentLoop);
			path = catLoop.getParent()+"/"+path;
			parentLoop = catLoop.getParent();
		}
		System.out.println("getPath 4");
		
		return path;
	}

	@Override
	public String getPathUI(String category, String tranType){
		System.out.println("getPath 1");
		if ((category == null)||(tranType == null)){
			return "Both parameters are required!";
		}
		System.out.println("getPath 2 - "+category);
		Categories cat = repository.findBycategory(category);
		if (cat == null){
			Categories categories = new Categories(); 
			categories.setCategory(category);
			categories.setDescription("Transaction");
			String parent = "expenseUNCAT";
			if (tranType.equalsIgnoreCase("I"))
				parent = "incomeUNCAT";
			categories.setParent(parent);
			//categories.set_id(ObjectId.get());
			Categories newCategory = categories;
			cat = newCategory;
		}
		//System.out.println("getPath 3");
		
		String parentLoop = cat.getParent();
		String path = parentLoop+"/"+cat.getCategory();
		System.out.println("getPath 3a "+ parentLoop + " " + path);
		while (!(parentLoop.equalsIgnoreCase("none"))){
			Categories catLoop = repository.findBycategory(parentLoop);
			path = catLoop.getParent()+"/"+path;
			parentLoop = catLoop.getParent();
		}
		System.out.println("getPath 4");
		
		return path;
	}

	@Override
	public String allocateParent(Categories categories){
		Categories cat = repository.findBycategory(categories.getCategory());
		if (cat == null){
			return "Category not found - no records updated";
		}
		if (!((cat.getParent().equals("expenseUNCAT"))||(cat.getParent().equals("incomeUNCAT")))){
			return "Cannot update this parent, must be uncategorised - no records updated";
		}
		if(repository.findBycategory(categories.getParent()) == null){
			return "Parent category not found - no records updated";
		}
		cat.setParent(categories.getParent());
		repository.save(cat);
		return "Success!";
	}
	
    public String processCSVFile(MultipartFile file, String refresh){
    	String status = "Empty file!";
    	if (!(file.isEmpty())){
    		status = "File is being processed";
    		
    		if (refresh.equalsIgnoreCase("Y")){
    			System.out.println("Delete all records here");
    			List<Categories> allCat = getAllCategories();
    			for (Categories cat : allCat){
    				removeCategory(cat.get_id());
    			}
    		}
	    	BufferedReader br;
	    	
	    	try {
	
	    	     String line;
	    	     Categories category;
	    	     InputStream is = file.getInputStream();
	    	     br = new BufferedReader(new InputStreamReader(is));
	    	     while ((line = br.readLine()) != null) {
	    	          
	    	          System.out.println(line);
	    	          category = new Categories();
		    	  		StringTokenizer st = new StringTokenizer(line,",");  
		    			int count = 0;
		    	    	while (st.hasMoreTokens()) {  
		    	    		count++;
	
		    	    		if (count==1){category.setCategory(st.nextToken());}//category keyword
		    	    		else if (count==2){category.setDescription(st.nextToken());}//description
		    	    		else if (count==3){category.setParent(st.nextToken());}//parent
		    	        }
		    	    	createCategory(category);
	    	     }
	  
	    	  } catch (IOException e) {
	    	    System.err.println(e.getMessage());       
	    	  }
	    	
	    	
	    	status = "File processed";
    	}
    	
    	return status;
    }
}
