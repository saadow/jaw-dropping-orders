package repository;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import config.DataConfig;
import entity.Customer;
import entity.Salesreps;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
@Transactional
@Rollback
public class CustomerRepositoryIntegrationTest {
	private static final BigDecimal NOT_EXIST_CUSTOMER = BigDecimal.valueOf(-1);
	private static final BigDecimal ALREADY_EXIST_CUSTOMER = BigDecimal.valueOf(2111);
	private static final Customer CUSTOMER = new Customer(BigDecimal.valueOf(1100), "Jirniy Kon'", new Salesreps(),
			BigDecimal.valueOf(182));

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void testGetAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		System.out.println(customers);
		assertTrue(customers.size() > 15);
	}
	
}
