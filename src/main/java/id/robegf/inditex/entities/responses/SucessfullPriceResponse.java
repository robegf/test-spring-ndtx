package id.robegf.inditex.entities.responses;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import id.robegf.inditex.entities.Price;

public class SucessfullPriceResponse implements PriceResponse {

	private static final long serialVersionUID = -5535273198854481111L;
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
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

	public String getStartDate() {
		return sdf.format(price.getStartDate());
	}

	public String getEndDate() {
		return sdf.format(price.getEndDate());
	}

	public BigDecimal getPrice() {
		return price.getProductPrice();
	}
}
