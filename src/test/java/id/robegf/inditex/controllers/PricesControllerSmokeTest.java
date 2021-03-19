package id.robegf.inditex.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PricesControllerSmokeTest {

	@Autowired
	private PricesController pricesController;

	@Test
	void contextLoads() throws Exception {
		assertThat(pricesController).isNotNull();
	}
}
