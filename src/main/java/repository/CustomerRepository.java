package repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, BigDecimal> {

	@Cacheable("findByCreditLimitGreaterThan")
	List<Customer> findByCreditLimitIsGreaterThan(BigDecimal creditLimit);

}
