package entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "SALESREPS", schema = "MA_STUDENT")
public class Salesreps implements Serializable {

	@Id
	@Column(name = "EMPL_NUM")
	private BigDecimal emplNum;
	private String name;
	private BigDecimal age;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REP_OFFICE")
	private Office repOffice;
	private String title;
	@Column(name = "HIRE_DATE")
	private Date hireDate;
	private BigDecimal manager;
	private BigDecimal quota;
	private BigDecimal sales;

	public Salesreps() {
	}

	public Salesreps(BigDecimal emplNum) {
		this.emplNum = emplNum;
	}

	public Salesreps(BigDecimal emplNum, String name, BigDecimal age, Office repOffice, String title, Date hireDate,
			BigDecimal manager, BigDecimal quota, BigDecimal sales) {
		this.emplNum = emplNum;
		this.name = name;
		this.age = age;
		this.repOffice = repOffice;
		this.title = title;
		this.hireDate = hireDate;
		this.manager = manager;
		this.quota = quota;
		this.sales = sales;
	}

	public BigDecimal getEmplNum() {
		return emplNum;
	}

	public void setEmplNum(BigDecimal emplNum) {
		this.emplNum = emplNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAge() {
		return age;
	}

	public void setAge(BigDecimal age) {
		this.age = age;
	}

	public Office getRepOffice() {
		return repOffice;
	}

	public void setRepOffice(Office repOffice) {
		this.repOffice = repOffice;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getHirerDate() {
		return hireDate;
	}

	public void setHirerDate(Date hirerDate) {
		this.hireDate = hirerDate;
	}

	public BigDecimal getManager() {
		return manager;
	}

	public void setManager(BigDecimal manager) {
		this.manager = manager;
	}

	public BigDecimal getQuota() {
		return quota;
	}

	public void setQuota(BigDecimal quota) {
		this.quota = quota;
	}

	public BigDecimal getSales() {
		return sales;
	}

	public void setSales(BigDecimal sales) {
		this.sales = sales;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((emplNum == null) ? 0 : emplNum.hashCode());
		result = prime * result + ((hireDate == null) ? 0 : hireDate.hashCode());
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((quota == null) ? 0 : quota.hashCode());
		result = prime * result + ((sales == null) ? 0 : sales.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Salesreps other = (Salesreps) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (emplNum == null) {
			if (other.emplNum != null)
				return false;
		} else if (!emplNum.equals(other.emplNum))
			return false;
		if (hireDate == null) {
			if (other.hireDate != null)
				return false;
		} else if (!hireDate.equals(other.hireDate))
			return false;
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (quota == null) {
			if (other.quota != null)
				return false;
		} else if (!quota.equals(other.quota))
			return false;
		if (sales == null) {
			if (other.sales != null)
				return false;
		} else if (!sales.equals(other.sales))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Salesreps [emplNum=" + emplNum + ", name=" + name + ", age=" + age + ", title=" + title + ", hireDate="
				+ hireDate + ", manager=" + manager + ", quota=" + quota + ", sales=" + sales + "]";
	}

}
