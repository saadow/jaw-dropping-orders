import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import controller.CustomerController;
import repository.CustomerRepository;

public class Main {
	private static final Logger LOG = LogManager.getLogger(CustomerController.class);

	private static final Logger LOGMAIL = LogManager.getLogger("error-logger");

	public static void main(String[] args) {
		LOG.error("HELLO >  > ?? ?");
		LOGMAIL.error("Exception when div working");
//		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext("config");
//
//		CustomerRepository customerRep = context.getBean(CustomerRepository.class);
//		customerRep.findAll().forEach(System.out::println);
	}

}
