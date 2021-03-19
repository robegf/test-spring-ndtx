package id.robegf.inditex.services;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import id.robegf.inditex.TestUtils;
import id.robegf.inditex.entities.Price;
import id.robegf.inditex.exceptions.PriceNotFoundException;
import id.robegf.inditex.repositories.PriceRepository;

public class UnitTestPriceService {

	@Mock
	private PriceRepository priceRepository;

	@InjectMocks
	private PriceService priceService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testExceptionThrown() {
		when(priceRepository.findByApplicationDateAndProductIdAndBrandId(TestUtils.APPLICATION_DATE, 0, 0))
		.thenThrow(PriceNotFoundException.class);

		assertThrows(PriceNotFoundException.class, () -> {
			priceService.getPrice(TestUtils.APPLICATION_DATE, 0, 0);
		});
	}

	@Test
	public void testCorrectResponse() throws Exception {
		final Price defaultPrice = TestUtils.createDefaultPrice();
		when(priceRepository.findByApplicationDateAndProductIdAndBrandId(TestUtils.APPLICATION_DATE, TestUtils.PRODUCT_ID, TestUtils.BRAND_ID))
		.thenReturn(defaultPrice);

		final Price price = priceService.getPrice(TestUtils.APPLICATION_DATE, TestUtils.PRODUCT_ID, TestUtils.BRAND_ID);

		assertEquals(TestUtils.BRAND_ID, price.getBrandId().intValue());
		assertEquals(TestUtils.PRODUCT_ID, price.getProductId().intValue());
		assertEquals(TestUtils.PRICE, price.getProductPrice());
	}
}
