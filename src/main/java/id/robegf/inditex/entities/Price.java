package id.robegf.inditex.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PRICES")
@NamedQuery(name = "Price.findByApplicationDateAndProductIdAndBrandId", query = "FROM Price p where p.productId = ?1 and p.brandId = ?2 and (p.startDate <= ?3 and p.endDate >=?3) ORDER BY p.priority DESC")
public class Price implements Serializable {

	private static final long serialVersionUID = 2561402950711957339L;

	@Id
	@Column(name = "PRICE_LIST", nullable = false)
	private Integer priceList;

	@Column(name = "BRAND_ID", nullable = false)
	private Integer brandId;

	@Column(name = "PRODUCT_ID", nullable = false)
	private Integer productId;

	@Column(name = "PRIORITY", nullable = false)
	private short priority;

	@Column(name = "PRICE", nullable = false)
	private BigDecimal productPrice;

	@Column(name = "CURR", nullable = false, length = 3)
	private String currency;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_DATE", nullable = false)
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DATE", nullable = false)
	private Date endDate;

	public Integer getPriceList() {
		return priceList;
	}

	public void setPriceList(final Integer priceList) {
		this.priceList = priceList;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(final Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(final Integer productId) {
		this.productId = productId;
	}

	public short getPriority() {
		return priority;
	}

	public void setPriority(final short priority) {
		this.priority = priority;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(final BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(final String currency) {
		this.currency = currency;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
}
