package id.robegf.inditex.entities.responses;

import id.robegf.inditex.entities.Price;

public class PriceResponseFactory {

	private PriceResponseFactory() {
		super();
	}

	public static PriceResponse createResponse(final Price aPrice) {
		if (aPrice == null) {
			return new UnsucessfulPriceResponse();
		}
		return new SucessfullPriceResponse(aPrice);
	}
}
