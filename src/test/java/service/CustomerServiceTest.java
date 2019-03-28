package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.PersistentObjectException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.MockitoJUnitRunner;

import entity.Customer;
import repository.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
	@Spy
	@InjectMocks
	private CustomerService customerService = new CustomerServiceImpl();
	@Mock
	private CustomerRepository customerRepository;

	private Customer testCustomer1 = new Customer();
	private Customer testCustomer2 = new Customer();

	@Test
	public void testGetAllCustomers() {
		List<Customer> customers = Arrays.asList(new Customer[] { testCustomer1, testCustomer2 });
		doReturn(customers).when(customerRepository).findAll();
		Set<Customer> result = customerService.getAllCustomers();
		assertTrue(customers.containsAll(result) && result.containsAll(customers));
	}

	@Test
	public void testFindCustomerByIdExists() {
		doReturn(Optional.of(testCustomer1)).when(customerRepository).findById(BigDecimal.valueOf(2111));
		Customer result = customerService.findCustomerById(BigDecimal.valueOf(2111));
		assertEquals(testCustomer1, result);
	}

	@Test(expected = NullPointerException.class)
	public void testFindCustomerByIdDoesNotExist() {
		doReturn(null).when(customerRepository).findById(BigDecimal.valueOf(1109));
		customerService.findCustomerById(BigDecimal.valueOf(1109));
	}

	@Test
	public void testInsertCustomer() {
		customerService.insertCustomer(testCustomer1);
		verify(customerRepository, times(1)).save(any());
	}
	
	@Test(expected =  RuntimeException.class)
	public void testInsertCustomerAlreadyExist() {
		when(customerRepository.save(testCustomer1)).thenThrow(new  RuntimeException());
		customerService.insertCustomer(testCustomer1);
	}
	
	@Test
	public void testUpdateCustomer() {
		doReturn(testCustomer1).when(customerRepository).save(any());
		customerService.updateCustomer(testCustomer1);
		verify(customerRepository, times(1)).save(any());
	}
	
	@Test(expected =  RuntimeException.class)
	public void testUpdateCustomerNotExist() {
		Customer customer = new Customer();
		when(customerRepository.save(customer)).thenThrow(new  RuntimeException());
		customerService.updateCustomer(customer);
	}

	@Test
	public void testDeleteCustomer() {
		doNothing().when(customerRepository).deleteById(any());
		customerService.deleteCustomer(testCustomer1.getCustNum());
		verify(customerRepository, times(1)).deleteById(any());
	}
	
	@Test
	public void testDeleteCustomerNotExist() {
		Customer customer = new Customer();
		doNothing().when(customerRepository).deleteById(customer.getCustNum());
		customerService.deleteCustomer(customer.getCustNum());
		verify(customerRepository, times(1)).deleteById(any());
	}

}
