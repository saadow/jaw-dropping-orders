package controller;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dto.CustomerRequest;
import entity.Customer;
import exception.UpdateException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import service.CustomerCreator;
import service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	private static final Logger LOG = LogManager.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerCreator customerCreator;

	@GetMapping("/{id}")
	public @ResponseBody Customer getCustomerById(@PathVariable("id") int id) {

		LOG.info("getCustomerById start, id={}", id);
		Customer result = customerService.findCustomerById(BigDecimal.valueOf(id));
		LOG.info("getCustomerById end");
		return result;
	}

	@PostMapping
	@ApiOperation(authorizations = { @Authorization(value = "basicAuth") }, value = "addCustomer")
	public void addCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
		LOG.info("Adding Customer id={}", customerRequest.getCustNum());
		Customer findCustomer = customerService.findCustomerById(customerRequest.getCustNum());
		if (Objects.nonNull(findCustomer)) {
			LOG.warn("Add failed, Customer already exists");
			throw new UpdateException(
					"Could not add Customer id=" + customerRequest.getCustNum() + ", because it is already exists.");
		} else {
			Customer addCustomer = customerCreator.createCustomer(customerRequest);
			customerService.insertCustomer(addCustomer);
		}
		LOG.info("Add Customer completed");
	}

	@DeleteMapping("/{id}")
	@ApiOperation(authorizations = { @Authorization(value = "basicAuth") }, value = "deleteCustomer")
	public void deleteCustomerById(@PathVariable("id") int id) {
		LOG.info("Deleting Customer id={}", id);
		Customer customer = customerService.findCustomerById(BigDecimal.valueOf(id));
		if (Objects.isNull(customer)) {
			LOG.warn("Delete failed, Customer is Null");
			throw new UpdateException("Could not delete Customer id=" + id + ", because it does not exist.");
		} else {
			customerService.deleteCustomer(BigDecimal.valueOf(id));
		}
		LOG.info("Delete Customer completed.");
	}

	@PutMapping("/{id}")
	@ApiOperation(authorizations = { @Authorization(value = "basicAuth") }, value = "updateCustomer")
	public void updateCustomerById(@PathVariable("id") int id, @RequestParam("creditLimit") Integer creditLimit) {
		LOG.info("Updating Customer id={}, creditLimit={}", id, creditLimit);
		Customer customer = customerService.findCustomerById(BigDecimal.valueOf(id));
		if (Objects.isNull(customer)) {
			LOG.warn("Update failed, Customer is Null");
			throw new UpdateException("Could not update Customer id=" + id + ", because it does not exist.");
		} else {
			customer.setCreditLimit(BigDecimal.valueOf(creditLimit));
			customerService.updateCustomer(customer);
		}
		LOG.info("Update Customer completed.");
	}

	@GetMapping
	public @ResponseBody Set<Customer> getCustomersCreditLimitMoreThan(
			@RequestParam(value = "creditLimit", required = false) BigDecimal creditLimit) {
		LOG.info("Getting Customer with creditLimit > {}", creditLimit);
		if (Objects.isNull(creditLimit)) {
			LOG.debug("getCustomersCreditLimitMoreThan uses getAllCustomers because creditLimit is Null");
			return customerService.getAllCustomers();
		}
		Set<Customer> result = customerService.getCustomersCreditLimitMoreThan(creditLimit);
		;
		LOG.info("getCustomersCreditLimitMoreThan completed.");
		return result;
	}
}
