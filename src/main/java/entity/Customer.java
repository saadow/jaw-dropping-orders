package entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

@Entity
@Table(name = "CUSTOMERS", schema = "MA_STUDENT")
public class Customer implements java.io.Serializable {

	@Id
	@Column(name = "CUST_NUM")
	private BigDecimal custNum;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUST_REP")
	private Salesreps custRep;
	@Column(name = "CREDIT_LIMIT")
	private BigDecimal creditLimit;
	@Column(name = "COMPANY")
	private String company;

	public Customer() {
	}

	public Customer(BigDecimal custNum, String company, Salesreps custRep, BigDecimal creditLimit) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((creditLimit == null) ? 0 : creditLimit.hashCode());
		result = prime * result + ((custNum == null) ? 0 : custNum.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (creditLimit == null) {
			if (other.creditLimit != null)
				return false;
		} else if (!creditLimit.equals(other.creditLimit))
			return false;
		if (custNum == null) {
			if (other.custNum != null)
				return false;
		} else if (!custNum.equals(other.custNum))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer{" + "custNum=" + custNum + ", company=" + company + ", creditLimit=" + creditLimit + '}';
	}
}