package com.wondroussoft.electronics.models.services;

import java.util.List;

import com.wondroussoft.electronics.models.Category;

public interface CategoryService {
	List<Category> getAllCategories();
	Category getCategoryById(Long id);
	void saveCategory(Category category);
}
