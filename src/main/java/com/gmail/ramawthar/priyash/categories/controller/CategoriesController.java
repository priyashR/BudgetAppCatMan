package com.gmail.ramawthar.priyash.categories.controller;

import java.util.List;

//import javax.validation.Valid;
import jakarta.validation.Valid;

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
	  //public ResponseEntity<Object> createCategory(@RequestBody Categories categories) {
		  
			logger.info("Controller call to createCategory");
			categories.setParent(categories.getParent());
			categories.setCategory(categories.getCategory());
	    return ResponseEntity.created(categoriesService.createCategory(categories)).build();
	  }
/*/////PR010524 - testing		  
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
		
	  @RequestMapping(value = "/siblings/{category}", method = RequestMethod.GET)
	  public ResponseEntity<Object> getAllSiblings(@PathVariable("category") String category) {
		logger.info("Controller call to getAllSiblings");
		//logger.warn("------ warn level - Get all the categories------");
	    return new ResponseEntity<>(categoriesService.getAllSiblings(category), HttpStatus.OK);
	  } 
	  

	  @RequestMapping(value = "/children/{parent}", method = RequestMethod.GET)
	  public ResponseEntity<Object> getAllchildren(@PathVariable("parent") String parent) {
		logger.info("Controller call to getAllchildren");
		//logger.warn("------ warn level - Get all the categories------");
	    return new ResponseEntity<>(categoriesService.getAllChildren(parent), HttpStatus.OK);
	  } 
	  
	  @RequestMapping(value = "/uncategorized", method = RequestMethod.GET)
	  public ResponseEntity<Object> getAllUncategorized() {
		logger.info("Controller call to getAllUncategorized");
		//logger.warn("------ warn level - Get all the categories------");
	    return new ResponseEntity<>(categoriesService.getAllUncategorised(), HttpStatus.OK);
	  } 
	  
	  @RequestMapping(value = "/allocateParent", method = RequestMethod.PUT)
	  //public ResponseEntity<Object> allocateParent(@Valid @RequestBody Categories categories) {
	  public ResponseEntity<Object> allocateParent(@RequestBody Categories categories) {
			logger.info("Controller call to allocate a parent to an uncategorised category");
	    return new ResponseEntity<>(categoriesService.allocateParent(categories), HttpStatus.OK);
	  } 
	  
	  @RequestMapping(value = "/fetchPath", method = RequestMethod.POST)
	  //public ResponseEntity<Object> fetchPath(@Valid @RequestBody FetchPathInput input) {
	  public ResponseEntity<Object> fetchPath(@RequestBody FetchPathInput input) {
			logger.info("Controller call to fetchPath");
	    return new ResponseEntity<>(categoriesService.getPath(input.getCategory(), input.getTranType()), HttpStatus.OK);
	  }
	/////PR010524 - testing	*/
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
