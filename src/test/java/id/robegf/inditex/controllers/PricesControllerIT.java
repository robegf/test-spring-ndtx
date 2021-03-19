package id.robegf.inditex.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import id.robegf.inditex.TestUtils;

@SpringBootTest
@AutoConfigureMockMvc
class PricesControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testHttpMethods() throws Exception {
		final String fullURL = generateCorrectURL(true);

		mockMvc.perform(get(TestUtils.SERVICE_CONTEXT)).andExpect(status().isUnsupportedMediaType());

		mockMvc.perform(get(fullURL))
		.andExpect(status().isUnsupportedMediaType());

		mockMvc.perform(post(TestUtils.SERVICE_CONTEXT)).andExpect(status().isMethodNotAllowed());

		mockMvc.perform(post(fullURL))
		.andExpect(status().isMethodNotAllowed());

		mockMvc.perform(put(TestUtils.SERVICE_CONTEXT)).andExpect(status().isMethodNotAllowed());

		mockMvc.perform(put(fullURL))
		.andExpect(status().isMethodNotAllowed());

		mockMvc.perform(delete(TestUtils.SERVICE_CONTEXT)).andExpect(status().isMethodNotAllowed());

		mockMvc.perform(delete(fullURL))
		.andExpect(status().isMethodNotAllowed());
	}

	@Test
	void testHeaders() throws Exception {
		final String correctURL = generateCorrectURL(true);
		final String wrongURL = generateCorrectURL(false);

		// No parameters
		mockMvc.perform(get(TestUtils.SERVICE_CONTEXT).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());

		mockMvc.perform(get(TestUtils.SERVICE_CONTEXT).contentType(MediaType.APPLICATION_XML))
		.andExpect(status().isUnsupportedMediaType());

		mockMvc.perform(get(TestUtils.SERVICE_CONTEXT).contentType(MediaType.APPLICATION_OCTET_STREAM))
		.andExpect(status().isUnsupportedMediaType());

		mockMvc.perform(get(TestUtils.SERVICE_CONTEXT).contentType(MediaType.APPLICATION_PDF))
		.andExpect(status().isUnsupportedMediaType());

		// Right set of parameters
		mockMvc.perform(
				get(correctURL)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

		mockMvc.perform(
				get(correctURL).contentType(MediaType.APPLICATION_XML))
		.andExpect(status().isUnsupportedMediaType());

		mockMvc.perform(get(correctURL)
				.contentType(MediaType.APPLICATION_OCTET_STREAM))
		.andExpect(status().isUnsupportedMediaType());

		mockMvc.perform(
				get(correctURL).contentType(MediaType.APPLICATION_PDF))
		.andExpect(status().isUnsupportedMediaType());
		// Wrong set of parameters
		mockMvc.perform(
				get(wrongURL).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());

		mockMvc.perform(
				get(wrongURL).contentType(MediaType.APPLICATION_XML))
		.andExpect(status().isUnsupportedMediaType());

		mockMvc.perform(get(wrongURL)
				.contentType(MediaType.APPLICATION_OCTET_STREAM)).andExpect(status().isUnsupportedMediaType());

		mockMvc.perform(
				get(wrongURL).contentType(MediaType.APPLICATION_PDF))
		.andExpect(status().isUnsupportedMediaType());
	}

	@Test
	void testResponse() throws Exception {
		final String fullURL = generateCorrectURL(true);

		mockMvc.perform(
				get(fullURL).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(jsonPath("price").exists())
		.andExpect(jsonPath("price").isNotEmpty())
		.andExpect(jsonPath("price", is(TestUtils.PRICE.doubleValue())))
		.andExpect(jsonPath("productId").exists())
		.andExpect(jsonPath("productId").isNotEmpty())
		.andExpect(jsonPath("productId", is(TestUtils.PRODUCT_ID)))
		.andExpect(jsonPath("brandId").exists()).andExpect(jsonPath("brandId").isNotEmpty())
		.andExpect(jsonPath("brandId", is(TestUtils.BRAND_ID))).andExpect(jsonPath("startDate").exists())
		.andExpect(jsonPath("startDate").isNotEmpty()).andExpect(jsonPath("startDate").isString())
		.andExpect(jsonPath("endDate").exists())
		.andExpect(jsonPath("endDate").isNotEmpty()).andExpect(jsonPath("endDate").isString());
	}

	@Test
	void testBusinessCases() throws Exception {
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

	private void assertValues(final String dateInStringFormat, final String doubleAsString) throws Exception {
		final StringBuilder builder = new StringBuilder(TestUtils.SERVICE_CONTEXT);
		builder.append(TestUtils.generateQueryString(dateInStringFormat, TestUtils.PRODUCT_ID, TestUtils.BRAND_ID));
		final String url = builder.toString();

		mockMvc.perform(
				get(url).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(jsonPath("price").exists())
		.andExpect(jsonPath("price").isNotEmpty())
		.andExpect(jsonPath("price", is(Double.parseDouble(doubleAsString))));
	}

	private String generateCorrectURL(final boolean isCorrect) {
		final StringBuilder builder = new StringBuilder(TestUtils.SERVICE_CONTEXT);
		if (isCorrect) {
			builder.append(TestUtils.RIGHT_QUERY_STRING);
		} else {
			builder.append(TestUtils.WRONG_QUERY_STRING);
		}
		return builder.toString();
	}
}
