package com.gmail.ramawthar.priyash.categories.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Categories {
	@Id
	public ObjectId _id;
	
	public String category;
	public String description;
	public String parent;
	
	public Categories(){}
	
	public String get_id() { return _id.toHexString(); }
	public void set_id(ObjectId _id) { this._id = _id; }

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
	
	
	

}
