package com.gmail.ramawthar.priyash.categories.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.ramawthar.priyash.categories.dao.CategoriesRepository;
import com.gmail.ramawthar.priyash.categories.model.Categories;
import com.gmail.ramawthar.priyash.categories.services.CategoriesService;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//code example -> https://www.codementor.io/gtommee97/rest-api-java-spring-boot-and-mongodb-j7nluip8d
@RestController
public class CategoriesController {

    private final Logger logger = LoggerFactory.getLogger(CategoriesController.class);
    

	@Autowired
    CategoriesService categoriesService;
	
	  @RequestMapping(value = "/categories", method = RequestMethod.GET)
	  public ResponseEntity<Object> getAllCategories() {
		logger.info("Controller call to getAllCategories");
		//logger.warn("------ warn level - Get all the categories------");
	    return new ResponseEntity<>(categoriesService.getAllCategories(), HttpStatus.OK);
	  } 

	  @RequestMapping(value = "/newCategory", method = RequestMethod.POST)
	  public ResponseEntity<Object> createCategory(@Valid @RequestBody Categories categories) {
			logger.info("Controller call to createCategory");
	    return ResponseEntity.created(categoriesService.createCategory(categories)).build();
	  }
	  
	  @RequestMapping(value = "/removeCategory/{id}", method = RequestMethod.DELETE)
	  public void deleteCategory(@PathVariable("id") ObjectId id) {
			logger.info("Controller call to deleteCategory");
		  categoriesService.removeCategory(id);;
	  }
	  
	  @RequestMapping(value = "/category/{category}", method = RequestMethod.GET)
	  public ResponseEntity<Object> getCategory(@PathVariable("category") String category) {
			logger.info("Controller call to getCategory");
		  return new ResponseEntity<>(categoriesService.getCategory(category), HttpStatus.OK);
	  }	  
    
/*
	  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	  public Pets getPetById(@PathVariable("id") ObjectId id) {
	    return repository.findBy_id(id);
	  }
	  
	  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	  public void modifyPetById(@PathVariable("id") ObjectId id, @Valid @RequestBody Pets pets) {
	    pets.set_id(id);
	    repository.save(pets);
	  }
	  

	  
*/

}


/*   works without service impl! 
@Autowired
private CategoriesRepository repository;


  @RequestMapping(value = "/categories", method = RequestMethod.GET)
  public List<Categories> getAllCategories() {
	logger.info("------ info level - Get all the categories------");
	logger.warn("------ warn level - Get all the categories------");
    return repository.findAll();
  }
  */
