package com.backbase.tinyexpression;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.backbase.tinyexpression.controller.URLController;

/**
 * Test cases for {@link TinyExpressionApplication}.
 */
@SpringBootTest
public class TinyExpressionApplicationTest {

	@Autowired
	private URLController urlController;

	/**
	 * Tests that the context loads properly and beans are initialized.
	 */
	@Test
	public void contextLoads() {
		assertThat(urlController).isNotNull();
	}

}
