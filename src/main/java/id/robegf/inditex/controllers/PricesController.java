package id.robegf.inditex.controllers;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.robegf.inditex.entities.Price;
import id.robegf.inditex.entities.responses.PriceResponse;
import id.robegf.inditex.entities.responses.PriceResponseFactory;
import id.robegf.inditex.exceptions.PriceNotFoundException;
import id.robegf.inditex.services.PriceService;

/**
 * PricesController
 *
 * @author robegf
 *
 */
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = "/api")
public class PricesController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PriceService priceService;

	@GetMapping("/prices")
	public ResponseEntity<PriceResponse> get(
			@RequestParam(value = "applicationDate") @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss") final Date applicationDate,
			@RequestParam(value = "productId") final Integer productId,
			@RequestParam(value = "brandId") final Integer brandId) {
		log.debug("get request received");
		try {
			final Price price = priceService.getPrice(applicationDate, productId, brandId);
			log.debug("Price found -> {}", price);
			final PriceResponse priceResponse = PriceResponseFactory.createResponse(price);
			return ResponseEntity.ok(priceResponse);
		} catch (final PriceNotFoundException pnfe) {
			log.debug("Price not found");
			final PriceResponse priceResponse = PriceResponseFactory.createResponse(null);
			return ResponseEntity.badRequest().body(priceResponse);
		}
	}
}
