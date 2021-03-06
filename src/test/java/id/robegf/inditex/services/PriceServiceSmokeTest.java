package id.robegf.inditex.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import id.robegf.inditex.repositories.PriceRepositoryImpl;

@SpringBootTest
class PriceServiceSmokeTest {

	@Autowired
	private PriceRepositoryImpl priceRepository;

	@Test
	void contextLoads() throws Exception {
		assertThat(priceRepository).isNotNull();
	}
}
