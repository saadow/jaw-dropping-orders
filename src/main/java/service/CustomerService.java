package service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import entity.Customer;

public interface CustomerService {
	Set<Customer> getAllCustomers();
	
	Set<Customer> getCustomersCreditLimitMoreThan(BigDecimal creditLimit);
	
	Customer findCustomerById(BigDecimal id);

	void insertCustomer(Customer customer);

	void updateCustomer(Customer customer);

	void deleteCustomer(BigDecimal custNum);
}
