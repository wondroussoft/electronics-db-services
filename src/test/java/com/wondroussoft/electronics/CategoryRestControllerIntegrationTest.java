package com.wondroussoft.electronics;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
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
import com.wondroussoft.electronics.controllers.CategoriesRestController;
import com.wondroussoft.electronics.models.Category;
import com.wondroussoft.electronics.models.services.CategoryService;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoriesRestController.class)
public class CategoryRestControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CategoryService categoryService;

	@Test
	public void givenCategories_whenGetCategories_thenReturnJsonArray() throws Exception {
		List<Category> cList = new ArrayList<Category>();

		Category c1 = new Category();

		c1.setId(1L);
		c1.setName("Mobiles");

		cList.add(c1);

		Category c2 = new Category();

		c2.setId(2L);
		c2.setName("Laptops");
		cList.add(c2);

		Mockito.when(categoryService.getAllCategories()).thenReturn(cList);

		mvc.perform(get("/api/categories").contentType(MediaType.TEXT_HTML)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].name", is(c1.getName())))
				.andExpect(jsonPath("$[1].name", is(c2.getName())));
	}
}