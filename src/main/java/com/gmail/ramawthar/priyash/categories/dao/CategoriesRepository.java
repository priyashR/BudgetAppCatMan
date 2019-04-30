package com.gmail.ramawthar.priyash.categories.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.gmail.ramawthar.priyash.categories.model.Categories;


public interface CategoriesRepository extends MongoRepository<Categories, String> {
	Categories findBy_id(ObjectId _id);
	Categories findBycategory(String category);
}
