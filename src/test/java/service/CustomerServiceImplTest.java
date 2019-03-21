package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import entity.Customer;
import repository.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {
	@Spy
	@InjectMocks
	private CustomerService customerService = new CustomerServiceImpl();
	@Mock
	private CustomerRepository customerRepository;

	private Customer testCustomer1 = new Customer();
	private Customer testCustomer2 = new Customer();

	@Test
	public void testGetAllCustomer() {
		List<Customer> customers = Arrays.asList(new Customer[] { testCustomer1, testCustomer2 });
		doReturn(customers).when(customerRepository).findAll();
		Set<Customer> result = customerService.getAllCustomers();
		assertTrue(customers.containsAll(result) && result.containsAll(customers));
	}

	@Test
	public void testInsertCustomer() {
		doReturn(testCustomer1).when(customerRepository).save(any());
		customerService.insertCustomer(testCustomer1);
		verify(customerRepository, times(1)).save(any());
	}

	
	@Test
	public void testUpdateCustomer() {
		doReturn(testCustomer1).when(customerRepository).save(any());
		customerService.updateCustomer(testCustomer1);
		verify(customerRepository, times(1)).save(any());
	}

	@Test
	public void testDeleteCustomer() {
		doNothing().when(customerRepository).deleteById(any());
		customerService.deleteCustomer(testCustomer1.getCustNum());
		verify(customerRepository, times(1)).deleteById(any());
	}

	@Test
	public void testFindCustomerById2111() {
		doReturn(Optional.of(testCustomer1)).when(customerRepository).findById(BigDecimal.valueOf(2111));
		Customer result = customerService.findCustomerById(BigDecimal.valueOf(2111));
		assertEquals(testCustomer1, result);
	}
}
