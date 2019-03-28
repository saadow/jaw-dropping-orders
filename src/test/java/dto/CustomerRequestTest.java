package dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;

import org.hibernate.validator.HibernateValidator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import util.CustomerDtoModelsUtil;

public class CustomerRequestTest {
	private Validator validator;

	@Before
	public void setUp() {
		LocalValidatorFactoryBean localValidatorFactory = new LocalValidatorFactoryBean();
		localValidatorFactory.setProviderClass(HibernateValidator.class);
		localValidatorFactory.afterPropertiesSet();
		validator = localValidatorFactory;
	}

	@Test
	public void testCustomerRequestValid() {
		CustomerRequest customerRequest = CustomerDtoModelsUtil.customerRequest();
		Errors errors = new BeanPropertyBindingResult(customerRequest, "customerRequest");
		validator.validate(customerRequest, errors);
		assertFalse(errors.hasErrors());
	}

	@Test
	public void testCustomerRequestCustNumIsNull() {
		CustomerRequest customerRequest = CustomerDtoModelsUtil.customerRequest();
		customerRequest.setCustNum(null);
		Errors errors = new BeanPropertyBindingResult(customerRequest, "customerRequest");
		validator.validate(customerRequest, errors);
		assertEquals(errors.getFieldError("custNum").getDefaultMessage(), "1");
	}

	@Test
	public void testCustomerRequestCustNumWrongFormat() {
		CustomerRequest customerRequest = CustomerDtoModelsUtil.customerRequest();
		customerRequest.setCustNum(BigDecimal.valueOf(11111));
		Errors errors = new BeanPropertyBindingResult(customerRequest, "customerRequest");
		validator.validate(customerRequest, errors);
		assertEquals(errors.getFieldError("custNum").getDefaultMessage(), "2");
	}

	@Test
	public void testCustomerRequestCustomerDetailsIsNull() {
		CustomerRequest customerRequest = CustomerDtoModelsUtil.customerRequest();
		customerRequest.setCustomerDetails(null);
		Errors errors = new BeanPropertyBindingResult(customerRequest, "customerRequest");
		validator.validate(customerRequest, errors);
		assertEquals(errors.getFieldError("customerDetails").getDefaultMessage(), "3");
	}
}
