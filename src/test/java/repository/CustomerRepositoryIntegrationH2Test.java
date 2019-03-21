package repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.DataConfig;
import entity.Customer;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
@TestPropertySource("classpath:test.properties")
public class CustomerRepositoryIntegrationH2Test {
	private static final Customer CUSTOMER_FOR_INSERT = new Customer(BigDecimal.valueOf(1101), null, null, null);
	private static final Customer CUSTOMER_FOR_UPDATE = new Customer(BigDecimal.valueOf(111111), "COMPANY1", null,
			BigDecimal.valueOf(32));
	private static final Customer CUSTOMER_FOR_DELETE = new Customer(BigDecimal.valueOf(1101), null, null, null);

	@Autowired
	private DataSource dataSource;

	@Autowired
	private CustomerRepository customerRepository;

	@Before
	public void initDB() {
		Resource initSchema = new ClassPathResource("scripts\\schema.sql");
		Resource data = new ClassPathResource("scripts\\data.sql");
		DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, data);
		DatabasePopulatorUtils.execute(databasePopulator, dataSource);
	}

	@Test
	public void testGetAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		customers.forEach(System.out::println);
		assertTrue(customers.size() == 2);
	}

	@Test
	public void testFindCustomerById() {
		assertNotNull(customerRepository.findById(BigDecimal.valueOf(111111)));
	}

	@Test
	public void testInsertCustomer() {
		customerRepository.save(CUSTOMER_FOR_INSERT);
	}

	@Test
	public void testUpdateCustomer() {
		CUSTOMER_FOR_UPDATE.setCreditLimit(BigDecimal.valueOf(19000));
		CUSTOMER_FOR_UPDATE.setCompany("YSL");
		customerRepository.save(CUSTOMER_FOR_UPDATE);
	}

	@Test
	public void testDeleteCustomer() {
		customerRepository.delete(CUSTOMER_FOR_DELETE);
	}
}
