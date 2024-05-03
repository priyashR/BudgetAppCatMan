package com.gmail.ramawthar.priyash.categories.model;

import jakarta.persistence.*;


@Entity
@Table(name = "Categories")
public class Categories {
	
	private long _id;
	
	private String category;
	private String description;
	private String parent;
	
	public Categories(){}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long get_id() { return _id; }
	public void set_id(long _id) { this._id = _id; }

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
