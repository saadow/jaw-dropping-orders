package entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "CUSTOMERS", schema = "MA_STUDENT")
public class Customer implements java.io.Serializable {

	@Id
	@Column(name = "CUST_NUM")
	private BigDecimal custNum;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CUST_REP")
	private Salesreps custRep;
	@Column(name = "CREDIT_LIMIT")
	private BigDecimal creditLimit;
	private String company;

	public Customer() {
	}

	public Customer(BigDecimal custNum, Salesreps custRep, BigDecimal creditLimit, String company) {
		this.custNum = custNum;
		this.custRep = custRep;
		this.creditLimit = creditLimit;
		this.company = company;
	}

	public BigDecimal getCustNum() {
		return custNum;
	}

	public void setCustNum(BigDecimal cust_num) {
		this.custNum = cust_num;
	}

	public Salesreps getCustRep() {
		return custRep;
	}

	public void setCustRep(Salesreps custRep) {
		this.custRep = custRep;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal credit_limit) {
		this.creditLimit = credit_limit;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Customer{" + "custNum=" + custNum + "company=" + company + ", custRep=" + custRep + ", creditLimit=" + creditLimit
				+ '\'' + '}';
	}
}