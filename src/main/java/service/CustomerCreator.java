package service;

import dto.CustomerRequest;
import entity.Customer;

public interface CustomerCreator {
	Customer createCustomer(CustomerRequest customerRequest);
}
