package id.robegf.inditex.services;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import id.robegf.inditex.TestUtils;
import id.robegf.inditex.entities.Price;
import id.robegf.inditex.exceptions.PriceNotFoundException;
import id.robegf.inditex.repositories.PriceRepository;

public class UnitTestPriceService {

	@InjectMocks
	private PriceService priceService;

	@Mock
	private PriceRepository priceRepository;

	@Test
	public void testExceptionThrown() {
		MockitoAnnotations.initMocks(this);

		when(priceRepository.findByApplicationDateAndProductIdAndBrandId(TestUtils.APPLICATION_DATE, 0, 0))
		.thenThrow(PriceNotFoundException.class);

		assertThatExceptionOfType(PriceNotFoundException.class).isThrownBy(() -> {
			priceService.getPrice(TestUtils.APPLICATION_DATE, 0, 0);
		});
	}

	@Test
	public void testCorrectResponse() throws Exception {
		MockitoAnnotations.initMocks(this);

		final Price defaultPrice = TestUtils.createDefaultPrice();
		when(priceRepository.findByApplicationDateAndProductIdAndBrandId(TestUtils.APPLICATION_DATE,
				TestUtils.PRODUCT_ID, TestUtils.BRAND_ID))
		.thenReturn(defaultPrice);

		final Price price = priceService.getPrice(TestUtils.APPLICATION_DATE, TestUtils.PRODUCT_ID, TestUtils.BRAND_ID);

		assertThat(TestUtils.BRAND_ID).isEqualTo(price.getBrandId().intValue());
		assertThat(TestUtils.PRODUCT_ID).isEqualTo(price.getProductId().intValue());
		assertThat(TestUtils.PRICE).isEqualTo(price.getProductPrice());
	}
}
