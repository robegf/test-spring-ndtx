package id.robegf.inditex.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import id.robegf.inditex.entities.Price;
import id.robegf.inditex.exceptions.PriceNotFoundException;

@Component
public class PriceRepositoryImpl {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@PersistenceContext
	private EntityManager entityManager;

	public Price findByApplicationDateAndProductIdAndBrandId(final Date applicationDate, final Integer productId,
			final Integer brandId) throws PriceNotFoundException {
		final Query query = entityManager.createNamedQuery("Price.findByApplicationDateAndProductIdAndBrandId",
				Price.class);
		query.setParameter(1, productId);
		query.setParameter(2, brandId);
		query.setParameter(3, applicationDate);
		try {
			final List<Price> prices = query.getResultList();
			if (prices.isEmpty()) {
				throw new PriceNotFoundException();
			}
			return prices.get(0);
		} catch (final NoResultException nre) {
			log.error("Error retrieving Price", nre);
			throw new PriceNotFoundException();
		}
	}
}
