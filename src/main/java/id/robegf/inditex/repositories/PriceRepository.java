package id.robegf.inditex.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.robegf.inditex.entities.Price;
import id.robegf.inditex.exceptions.PriceNotFoundException;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {

	Price findByApplicationDateAndProductIdAndBrandId(final Date applicationDate, final Integer productId,
			final Integer brandId) throws PriceNotFoundException;
}