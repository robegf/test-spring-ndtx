package id.robegf.inditex.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import id.robegf.inditex.TestUtils;
import id.robegf.inditex.exceptions.PriceNotFoundException;
import id.robegf.inditex.services.PriceService;

@RunWith(SpringRunner.class)
@WebMvcTest(PricesController.class)
public class UnitTestPricesController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PriceService service;

	@Test
	public void testExceptionThrown() throws Exception {
		when(service.getPrice(TestUtils.APPLICATION_DATE, 0, 0)).thenThrow(PriceNotFoundException.class);
		mockMvc.perform(
				get(TestUtils.SERVICE_CONTEXT + TestUtils.RIGHT_QUERY_STRING).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("message", is("Price not found")));
	}

	@Test
	public void testCorrectResponse() throws Exception {
		when(service.getPrice(TestUtils.APPLICATION_DATE, TestUtils.PRODUCT_ID, TestUtils.BRAND_ID))
		.thenReturn(TestUtils.createDefaultPrice());
		mockMvc.perform(
				get(TestUtils.SERVICE_CONTEXT + TestUtils.RIGHT_QUERY_STRING).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("price", is(TestUtils.PRICE.doubleValue())))
		.andExpect(jsonPath("brandId", is(TestUtils.BRAND_ID)))
		.andExpect(jsonPath("productId", is(TestUtils.PRODUCT_ID)));
	}
}
