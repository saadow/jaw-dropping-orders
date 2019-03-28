package util;

import java.math.BigDecimal;

import dto.CustomerDetails;
import dto.CustomerRequest;
import entity.Customer;

public class CustomerDtoModelsUtil {
	private static final BigDecimal CUST_NUM = BigDecimal.valueOf(1988);
	private static final String COMPANY = "WILSON";
	private static final BigDecimal CREDIT_LIMIT = BigDecimal.valueOf(88);

	public static Customer customer() {
		Customer expected = new Customer();
		expected.setCustNum(CUST_NUM);
		expected.setCompany(COMPANY);
		expected.setCreditLimit(CREDIT_LIMIT);
		return expected;
	}

	public static CustomerRequest customerRequest() {
		CustomerRequest customerRequest = new CustomerRequest();
		customerRequest.setCustNum(CUST_NUM);
		customerRequest.setCustomerDetails(customerDetails());
		return customerRequest;
	}

	public static CustomerDetails customerDetails() {
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setCompany(COMPANY);
		customerDetails.setCreditLimit(CREDIT_LIMIT);
		return customerDetails;
	}
}
