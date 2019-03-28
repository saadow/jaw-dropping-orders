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

public class CustomerDetailsTest {
	private Validator validator;

	@Before
	public void setUp() {
		LocalValidatorFactoryBean localValidatorFactory = new LocalValidatorFactoryBean();
		localValidatorFactory.setProviderClass(HibernateValidator.class);
		localValidatorFactory.afterPropertiesSet();
		validator = localValidatorFactory;
	}

	@Test
	public void testCustomerDetailsValid() {
		CustomerDetails customerDetails = CustomerDtoModelsUtil.customerDetails();
		Errors errors = new BeanPropertyBindingResult(customerDetails, "customerDetails");
		validator.validate(customerDetails, errors);
		assertFalse(errors.hasErrors());
	}

	@Test
	public void testCustomerDetailsCompanyIsNull() {
		CustomerDetails customerDetails = CustomerDtoModelsUtil.customerDetails();
		customerDetails.setCompany(null);
		Errors errors = new BeanPropertyBindingResult(customerDetails, "customerDetails");
		validator.validate(customerDetails, errors);
		assertEquals(errors.getFieldError("company").getDefaultMessage(), "3");
	}

	@Test
	public void testCustomerDetailsCreditLimitIsNull() {
		CustomerDetails customerDetails = CustomerDtoModelsUtil.customerDetails();
		customerDetails.setCreditLimit(null);
		Errors errors = new BeanPropertyBindingResult(customerDetails, "customerDetails");
		validator.validate(customerDetails, errors);
		assertEquals(errors.getFieldError("creditLimit").getDefaultMessage(), "3");
	}
	
	@Test
	public void testCustomerDetailsCreditLimitIsLessThan() {
		CustomerDetails customerDetails = CustomerDtoModelsUtil.customerDetails();
		customerDetails.setCreditLimit(BigDecimal.valueOf(1));
		Errors errors = new BeanPropertyBindingResult(customerDetails, "customerDetails");
		validator.validate(customerDetails, errors);
		assertEquals(errors.getFieldError("creditLimit").getDefaultMessage(), "4");
	}

}
