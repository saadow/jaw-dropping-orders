package service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import entity.Customer;
import exception.DeleteException;
import repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOG = LogManager.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Set<Customer> getAllCustomers() {
		LOG.debug("get all customers");
		HashSet<Customer> result = new HashSet<Customer>(customerRepository.findAll());
		LOG.debug("{} results", result.size());
		return result;
	}

	@Override
	public void insertCustomer(Customer customer) {
		LOG.debug("insert customer = {}", customer);
		customerRepository.save(customer);
		LOG.debug("insert completed");
	}

	@Override
	public void updateCustomer(Customer customer) {
		LOG.debug("update customer={}", customer);
		customerRepository.save(customer);
		LOG.debug("update completed");
	}

	@Override
	public void deleteCustomer(BigDecimal custNum) {
		LOG.debug("delete customer by CustNum = {}", custNum);
		try {
			customerRepository.deleteById(custNum);
		} catch (EmptyResultDataAccessException e) {
			LOG.warn("Can't delete customer with id = {}", custNum + ", EmptyResultData");
			throw new DeleteException("Can't delete customer by Id = " + custNum + ", EmptyResultData");
		}
		LOG.debug("delete customer completed");
	}

}
