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
public class ElectronicsController {

	@Autowired
	CategoryService serviceCategory;

	@Autowired
	ProductRepo repoProduct;

	@GetMapping("/")
	public String getHomePage(Model model) {

		return "home";

	}

	@GetMapping("/categories")
	public String getElectronicsCategories(Model model) {

		List<Category> categories = serviceCategory.getAllCategories();

		model.addAttribute("categories", categories);

		return "categories";

	}

	@GetMapping("/products/category/{categoryId}")
	public String getProducts(Model model, @PathVariable(name = "categoryId") Long categoryId) {

		List<Product> products = repoProduct.findByCategoryId(categoryId);

		model.addAttribute("allproducts", products);
		model.addAttribute("categoryId", categoryId);

		return "products";

	}

	@GetMapping("/category/add")
	public String getAddCategoryForm() {

		return "add_category";
	}

	@GetMapping("/product/add/category/{categoryId}")
	public String getAddProductForm(Model model, @PathVariable(name = "categoryId") Long catgeoryId) {
		model.addAttribute("catgeoryId", catgeoryId);

		return "add_product";
	}

	@PostMapping("/category/add")
	public String saveCategory(Category category) {
		// Save new category
		serviceCategory.saveCategory(category);

		return "redirect:/categories";
	}

	@PostMapping("/product/add/category/{categoryId}")
	public String saveProduct(Model model, @PathVariable(name = "categoryId") Long categoryId, Product product) {
		// Save new product
		product.setCategoryId(categoryId);
		repoProduct.save(product);

		return "redirect:/products/category/" + categoryId;
	}

	@GetMapping("/product/{productId}/details")
	public String getProduct(Model model, @PathVariable(name = "productId") Long productId) {

		Product product = repoProduct.findById(productId).get();

		model.addAttribute("product", product);

		return "product_detail";

	}
}
