package id.robegf.inditex;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import id.robegf.inditex.entities.Price;

public class TestUtils {

	public static final String DEFAULT_PRICE = "35.50";
	public static final String DEFAULT_APPLICATION_DATE = "2020-06-14-10.00.00";
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd-HH.mm.ss";
	public static final int PRODUCT_ID = 35455;
	public static final int BRAND_ID = 1;
	public static final Date APPLICATION_DATE = createApplicationDate();
	public static final BigDecimal PRICE = new BigDecimal(DEFAULT_PRICE);
	public static final String SERVICE_HOST = "http://localhost:";
	public static final String SERVICE_CONTEXT = "/api/prices";
	public static final String RIGHT_QUERY_STRING = generateQueryString(DEFAULT_APPLICATION_DATE, PRODUCT_ID, BRAND_ID);
	public static final String WRONG_QUERY_STRING = generateQueryString(DEFAULT_APPLICATION_DATE, 0, 0);

	private static final Date createApplicationDate() {
		return convertStringToDate(DEFAULT_APPLICATION_DATE);
	}

	public static final Date convertStringToDate(final String dateInStringFormat) {
		Date date = null;
		try {
			date = new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(dateInStringFormat);
		} catch (final ParseException pe) {
			throw new RuntimeException(pe);
		}
		return date;
	}

	public static String generateQueryString(final String dateInStringFormat, final int aProductId,
			final int aBrandId) {
		final StringBuilder builder = new StringBuilder("?applicationDate=").append(dateInStringFormat)
				.append("&productId=").append(aProductId).append("&brandId=").append(aBrandId);
		return builder.toString();
	}

	public static Map<String, Object> createWrongSetOfParameters() {
		final Map<String, Object> parameters = new HashMap<String, Object>(3);
		parameters.put("applicationDate", new Date());
		parameters.put("brandId", 0);
		parameters.put("productId", 0);
		return parameters;
	}

	public static Map<String, Object> createRightSetOfParameters() throws Exception {
		final Map<String, Object> parameters = new HashMap<String, Object>(3);
		parameters.put("applicationDate", APPLICATION_DATE);
		parameters.put("brandId", BRAND_ID);
		parameters.put("productId", PRODUCT_ID);
		return parameters;
	}

	public static Price createEmptyPrice() {
		final Date today = new Date();
		final Price price = new Price();
		price.setBrandId(0);
		price.setCurrency("EUR");
		price.setEndDate(today);
		price.setPriceList(0);
		price.setPriority(Short.parseShort("0"));
		price.setProductId(0);
		price.setProductPrice(new BigDecimal("0.0"));
		price.setStartDate(today);
		return price;
	}

	public static Price createDefaultPrice() {
		final Price price = createEmptyPrice();
		price.setBrandId(BRAND_ID);
		price.setProductId(PRODUCT_ID);
		price.setProductPrice(PRICE);
		return price;
	}
}
