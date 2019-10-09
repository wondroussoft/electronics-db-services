package com.wondroussoft.electronics;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.wondroussoft.electronics.models.Category;
import com.wondroussoft.electronics.models.repos.CategoryRepo;
import com.wondroussoft.electronics.models.services.CategoryService;
import com.wondroussoft.electronics.models.services.impl.CategoryServiceImpl;

@RunWith(SpringRunner.class)
public class CategoryServiceImplIntegrationTest {

	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {

		@Bean
		public CategoryService categoryService() {
			return new CategoryServiceImpl();
		}
	}

	@Autowired
	private CategoryService categoryService;

	@MockBean
	private CategoryRepo categoryRepository;

	@Before
	public void setUp() {
		Category c = new Category();
		c.setName("Laptops");
		c.setId(2L);

		Mockito.when(categoryRepository.findById(c.getId())).thenReturn(Optional.of(c));
	}

	@Test
	public void whenValidId_thenCategoryShouldBeFound() {
		Long id = 2L;
		Category found = categoryService.getCategoryById(id);

		assertThat(found.getName()).isEqualTo("Laptops");
	}
}