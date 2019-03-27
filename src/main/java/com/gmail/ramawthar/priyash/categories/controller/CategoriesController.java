package com.gmail.ramawthar.priyash.categories.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.ramawthar.priyash.categories.dao.CategoriesRepository;
import com.gmail.ramawthar.priyash.categories.model.Categories;

@RestController
public class CategoriesController {
	@Autowired
	private CategoriesRepository repository;
	

	  @RequestMapping(value = "/categories", method = RequestMethod.GET)
	  public List<Categories> getAllCategories() {
	    return repository.findAll();
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
	  
	  @RequestMapping(value = "/", method = RequestMethod.POST)
	  public Pets createPet(@Valid @RequestBody Pets pets) {
	    pets.set_id(ObjectId.get());
	    repository.save(pets);
	    return pets;
	  }
	  
	  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	  public void deletePet(@PathVariable ObjectId id) {
	    repository.delete(repository.findBy_id(id));
	  }*/

}
