package entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PRODUCTS", schema = "MA_STUDENT")
public class Product implements java.io.Serializable {

	@Id
	@Column(name = "PRODUCT_ID")
	private String productId;
	@Column(name = "MFR_ID")
	private String mfrId;
	private String description;
	private BigDecimal price;
	@Column(name = "QTY_ON_HAND")
	private BigDecimal qtyOnHand;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	Set<Order> orders = new HashSet<Order>(0);

	public Product() {
	}

	public Product(String productId) {
		this.productId = productId;
	}

	public Product(String productId, String mfrId, String description, BigDecimal price, BigDecimal qtyOnHand,
			Set<Order> orders) {
		this.productId = productId;
		this.mfrId = mfrId;
		this.description = description;
		this.price = price;
		this.qtyOnHand = qtyOnHand;
		this.orders = orders;
	}

	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getMfrId() {
		return this.mfrId;
	}

	public void setMfrId(String mfrId) {
		this.mfrId = mfrId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getQtyOnHand() {
		return this.qtyOnHand;
	}

	public void setQtyOnHand(BigDecimal qtyOnHand) {
		this.qtyOnHand = qtyOnHand;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Set<Order> getOrders() {
		return this.orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((mfrId == null) ? 0 : mfrId.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((qtyOnHand == null) ? 0 : qtyOnHand.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (mfrId == null) {
			if (other.mfrId != null)
				return false;
		} else if (!mfrId.equals(other.mfrId))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (qtyOnHand == null) {
			if (other.qtyOnHand != null)
				return false;
		} else if (!qtyOnHand.equals(other.qtyOnHand))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", mfrId=" + mfrId + ", description=" + description + ", price="
				+ price + ", qtyOnHand=" + qtyOnHand + "]";
	}

}
