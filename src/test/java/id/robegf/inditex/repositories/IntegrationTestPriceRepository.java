package id.robegf.inditex.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import id.robegf.inditex.TestUtils;
import id.robegf.inditex.entities.Price;
import id.robegf.inditex.exceptions.PriceNotFoundException;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class IntegrationTestPriceRepository {

	@Autowired
	private PriceRepositoryImpl priceRepository;

	@Test
	public void testFindPrice() {
		final Price expectedPrice = TestUtils.createDefaultPrice();
		final Price price = priceRepository.findByApplicationDateAndProductIdAndBrandId(TestUtils.APPLICATION_DATE,
				TestUtils.PRODUCT_ID, TestUtils.BRAND_ID);

		assertThat(expectedPrice.getCurrency()).isEqualTo(price.getCurrency());
		assertThat(expectedPrice.getProductPrice()).isEqualTo(price.getProductPrice());
		assertThat(expectedPrice.getProductId()).isEqualTo(price.getProductId());
		assertThat(expectedPrice.getBrandId()).isEqualTo(price.getBrandId());
		assertThat(expectedPrice.getPriceList()).isZero();
		assertThat(expectedPrice.getPriority()).isZero();
		assertThat(expectedPrice.getStartDate()).isNotNull();
		assertThat(expectedPrice.getEndDate()).isNotNull();
	}

	@Test
	public void testPriceNotFound() {
		assertThrows(PriceNotFoundException.class, () -> {
			priceRepository.findByApplicationDateAndProductIdAndBrandId(TestUtils.APPLICATION_DATE, 0, 0);
		});
	}
}
