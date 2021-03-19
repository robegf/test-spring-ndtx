package id.robegf.inditex.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.robegf.inditex.entities.Price;
import id.robegf.inditex.exceptions.PriceNotFoundException;
import id.robegf.inditex.repositories.PriceRepository;

@Service
public class PriceService {

	@Autowired
	protected PriceRepository priceRepository;

	public Price getPrice(final Date applicationDate, final Integer productId, final Integer brandId)
			throws PriceNotFoundException {
		return priceRepository.findByApplicationDateAndProductIdAndBrandId(applicationDate,
				productId, brandId);
	}
}
