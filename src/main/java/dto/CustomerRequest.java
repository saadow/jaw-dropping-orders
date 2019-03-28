package dto;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

public class CustomerRequest {
	@NotNull(message = "1")
	@Min(value = 1000, message = "2")
	@Max(value = 9999, message = "2")
	private BigDecimal custNum;
	@Valid
	@NotNull(message = "3")
	private CustomerDetails customerDetails;

	public BigDecimal getCustNum() {
		return custNum;
	}

	public void setCustNum(BigDecimal custNum) {
		this.custNum = custNum;
	}

	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	@Override
	public String toString() {
		return "CustomerRequest [custNum=" + custNum + ", customerDetails=" + customerDetails + "]";
	}

}
