package com.wondroussoft.electronics.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wondroussoft.electronics.models.Category;
import com.wondroussoft.electronics.models.services.CategoryService;

@RestController
public class CategoriesRestController {

	@Autowired
	CategoryService serviceCategory;

	@GetMapping("/api/categories")
	public ResponseEntity<Object> getElectronicsCategoriesAsJsonArray() {

		List<Category> categories = serviceCategory.getAllCategories();

		return new ResponseEntity<Object>(categories, HttpStatus.OK);

	}
}
