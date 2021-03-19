package id.robegf.inditex.services;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import id.robegf.inditex.TestUtils;
import id.robegf.inditex.entities.Price;
import id.robegf.inditex.exceptions.PriceNotFoundException;

@SpringBootTest
class PriceServiceIT {

	@Autowired
	protected PriceService priceService;

	@Test
	void testPriceNotFound() {
		assertThatExceptionOfType(PriceNotFoundException.class).isThrownBy(() -> {
			priceService.getPrice(TestUtils.APPLICATION_DATE, 0, 0);
		});
	}

	@Test
	void testPriceFound() {
		final String date1 = TestUtils.DEFAULT_APPLICATION_DATE;
		final String date2 = "2020-06-14-16.00.00";
		final String date3 = "2020-06-14-21.00.00";
		final String date4 = "2020-06-15-10.00.00";
		final String date5 = "2020-06-16-21.00.00";
		final String price1 = TestUtils.DEFAULT_PRICE;
		final String price2 = "25.45";
		final String price3 = "30.50";
		final String price4 = "38.95";

		assertValues(date1, price1);
		assertValues(date2, price2);
		assertValues(date3, price1);
		assertValues(date4, price3);
		assertValues(date5, price4);
	}

	private void assertValues(final String dateParameter, final String priceParameter) {
		final Date d = TestUtils.convertStringToDate(dateParameter);
		final BigDecimal bd = new BigDecimal(priceParameter);
		final Price p = priceService.getPrice(d, TestUtils.PRODUCT_ID, TestUtils.BRAND_ID);
		assertThat(bd).isEqualTo(p.getProductPrice());
	}
}
