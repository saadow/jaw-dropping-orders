import java.math.BigDecimal;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import repository.CustomerRepository;

public class Main {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext("config");

		CustomerRepository customerRep = context.getBean(CustomerRepository.class);
		customerRep.findAll().forEach(System.out::println);
		customerRep.findByCustRep_AgeIsGreaterThan(BigDecimal.valueOf(50)).forEach(System.out::println);
		System.out.println("♡━━━━━━━━━━━━━━━♡ ｡･:*:･ﾟ★,｡･:*:･ﾟ☆ CACHING??? ｡･:*:･ﾟ★,｡･:*:･ﾟ☆ ☆━━━━━━━━━━━━━━━☆");
		customerRep.findByCustRep_AgeIsGreaterThan(BigDecimal.valueOf(50)).forEach(System.out::println);
	}

}
