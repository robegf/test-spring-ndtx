package id.robegf.inditex.services;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.robegf.inditex.TestUtils;
import id.robegf.inditex.entities.Price;
import id.robegf.inditex.exceptions.PriceNotFoundException;
import id.robegf.inditex.repositories.PriceRepository;

@ExtendWith(MockitoExtension.class)
class PriceServiceUnitTest {

	@InjectMocks
	private PriceService priceService;

	@Mock
	private PriceRepository priceRepository;

	@Test
	void testExceptionThrown() {
		when(priceRepository.findByApplicationDateAndProductIdAndBrandId(TestUtils.APPLICATION_DATE, 0, 0))
		.thenThrow(PriceNotFoundException.class);

		assertThatExceptionOfType(PriceNotFoundException.class).isThrownBy(() -> {
			priceService.getPrice(TestUtils.APPLICATION_DATE, 0, 0);
		});
	}

	@Test
	void testCorrectResponse() throws Exception {
		final Price defaultPrice = TestUtils.createDefaultPrice();
		when(priceRepository.findByApplicationDateAndProductIdAndBrandId(TestUtils.APPLICATION_DATE,
				TestUtils.PRODUCT_ID, TestUtils.BRAND_ID))
		.thenReturn(defaultPrice);

		final Price price = priceService.getPrice(TestUtils.APPLICATION_DATE, TestUtils.PRODUCT_ID, TestUtils.BRAND_ID);

		assertThat(price.getBrandId().intValue()).isEqualTo(TestUtils.BRAND_ID);
		assertThat(price.getProductId().intValue()).isEqualTo(TestUtils.PRODUCT_ID);
		assertThat(price.getProductPrice()).isEqualTo(TestUtils.PRICE);
	}
}
