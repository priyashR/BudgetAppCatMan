package com.gmail.ramawthar.priyash.categories.dao;

import java.util.List;

//import org.bson.types.ObjectId;
//import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.gmail.ramawthar.priyash.categories.model.Categories;


public interface CategoriesRepository extends JpaRepository<Categories, Long> {
	Categories findBy_id(Long _id);
	Categories findBycategory(String category);
	List<Categories> findByparent(String parent);
}
