package id.robegf.inditex.entities.responses;

import java.math.BigDecimal;
import java.util.Date;

import id.robegf.inditex.entities.Price;

public class SucessfullPriceResponse implements PriceResponse {

	private static final long serialVersionUID = -5535273198854481111L;
	private Price price = null;

	public SucessfullPriceResponse(final Price aPrice) {
		super();
		price = aPrice;
	}

	public Integer getProductId() {
		return price.getProductId();
	}

	public Integer getBrandId() {
		return price.getBrandId();
	}

	public Date getStartDate() {
		return price.getStartDate();
	}

	public Date getEndDate() {
		return price.getEndDate();
	}

	public BigDecimal getPrice() {
		return price.getProductPrice();
	}
}
