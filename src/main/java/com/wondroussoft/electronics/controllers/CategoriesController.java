package com.wondroussoft.electronics.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.wondroussoft.electronics.models.Category;
import com.wondroussoft.electronics.models.Product;
import com.wondroussoft.electronics.models.repos.CategoryRepo;
import com.wondroussoft.electronics.models.repos.ProductRepo;
import com.wondroussoft.electronics.models.services.CategoryService;

@Controller
public class CategoriesController {

	@Autowired
	CategoryService serviceCategory;

	@GetMapping("/categories")
	public String getElectronicsCategories(Model model) {

		List<Category> categories = serviceCategory.getAllCategories();

		model.addAttribute("categories", categories);

		return "categories";

	}
}
