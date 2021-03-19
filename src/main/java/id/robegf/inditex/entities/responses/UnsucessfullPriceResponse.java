package id.robegf.inditex.entities.responses;

public class UnsucessfullPriceResponse implements PriceResponse {


	private static final long serialVersionUID = -790930153725947694L;

	private static final String ERROR_MESSAGE = "Price not found";

	public UnsucessfullPriceResponse() {
		super();
	}


	public String getMessage() {
		return ERROR_MESSAGE;
	}
}
