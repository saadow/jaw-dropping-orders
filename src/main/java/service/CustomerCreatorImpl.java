package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import dto.CustomerDetails;
import dto.CustomerRequest;
import entity.Customer;

@Service
public class CustomerCreatorImpl implements CustomerCreator {
	private static final Logger LOG = LogManager.getLogger(CustomerCreatorImpl.class);

	@Override
	public Customer createCustomer(CustomerRequest customerRequest) {
		LOG.info("createCustomer, customerRequest={}", customerRequest);
		Customer result = new Customer();
		result.setCustNum(customerRequest.getCustNum());
		
		CustomerDetails customerDetails = customerRequest.getCustomerDetails();
		result.setCompany(customerDetails.getCompany());
		result.setCreditLimit(customerDetails.getCreditLimit());
		LOG.info("createCustomer, customer, that was created after={}", result);
		return result;
	}

}
