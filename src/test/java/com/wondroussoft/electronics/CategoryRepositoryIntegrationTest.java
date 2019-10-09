package com.wondroussoft.electronics;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wondroussoft.electronics.models.Category;
import com.wondroussoft.electronics.models.repos.CategoryRepo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryIntegrationTest {
	@Autowired
	private CategoryRepo categoryRepository;

	@Test
	public void whenFindById_thenReturnCategory() {
		// given
		Category c = new Category();

		c.setId(1L);
		c.setName("Mobiles");
		categoryRepository.save(c);

		// when
		Category found = categoryRepository.findById(c.getId()).get();

		// then
		assertThat(found.getName()).isEqualTo(c.getName());
	}
}