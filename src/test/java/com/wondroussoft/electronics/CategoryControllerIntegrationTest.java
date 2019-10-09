package com.wondroussoft.electronics;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.wondroussoft.electronics.controllers.CategoriesController;
import com.wondroussoft.electronics.models.Category;
import com.wondroussoft.electronics.models.services.CategoryService;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoriesController.class)
public class CategoryControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CategoryService categoryService;

	@Test
	public void givenCategories_whenGetCategories_thenReturnJsonArray() throws Exception {
		Category c = new Category();

		c.setId(1L);
		c.setName("Mobiles");

		List<Category> allCategories = Arrays.asList(c);

		Mockito.when(categoryService.getAllCategories()).thenReturn(allCategories);

		mvc.perform(get("/categories").contentType(MediaType.TEXT_HTML)).andExpect(status().isOk());
	}
}