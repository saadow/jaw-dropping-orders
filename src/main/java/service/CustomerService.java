package service;

import java.math.BigDecimal;
import java.util.Set;

import entity.Customer;

public interface CustomerService {
	Set<Customer> getAllCustomers();

	Customer findByCustNum(BigDecimal custNum);

	void insertCustomer(Customer customer);

	void updateCustomer(Customer customer);

	void deleteCustomer(BigDecimal custNum);
}
