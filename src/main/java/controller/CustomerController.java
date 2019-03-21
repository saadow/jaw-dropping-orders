package controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import entity.Customer;
import service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	private static final Logger LOG = LogManager.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@GetMapping("/{id}")
	public @ResponseBody Customer getCustomerById(@PathVariable("id") int id) {
		LOG.info("getCustomerById start, id={}", id);
		Customer result = customerService.findCustomerById(BigDecimal.valueOf(id));
		LOG.info("getCustomerById end");
		return result;
	}
}
