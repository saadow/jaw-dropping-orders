package dto;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CustomerDetails {
	@NotNull(message = "3")
	private String company;
	@Min(value = 10, message = "4")
	@NotNull(message = "3")
	private BigDecimal creditLimit;

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	@Override
	public String toString() {
		return "CustomerDetails [company=" + company + ", creditLimit=" + creditLimit + "]";
	}

}
