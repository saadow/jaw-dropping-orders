package service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dto.CustomerRequest;
import entity.Customer;
import util.CustomerDtoModelsUtil;

public class CustomerCreatorTest {
	private CustomerCreator customerCreator = new CustomerCreatorImpl();

	@Test
	public void testCreateCustomer() {
		CustomerRequest customerRequest = CustomerDtoModelsUtil.customerRequest();
		Customer actual = customerCreator.createCustomer(customerRequest);
		Customer expected = CustomerDtoModelsUtil.customer();
		assertEquals(expected, actual);
	}

}
