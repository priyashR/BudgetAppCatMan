package com.gmail.ramawthar.priyash.repositories;

import com.gmail.ramawthar.priyash.models.Categories;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CategoriesRepository extends MongoRepository<Categories, String> {
	Categories findBy_id(ObjectId _id);
}
