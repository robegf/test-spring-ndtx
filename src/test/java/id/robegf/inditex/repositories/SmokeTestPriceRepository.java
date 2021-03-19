package id.robegf.inditex.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class SmokeTestPriceRepository {

	@Autowired
	private EntityManager entityManager;

	@Test
	public void contextLoads() throws Exception {
		assertThat(entityManager).isNotNull();
	}
}
