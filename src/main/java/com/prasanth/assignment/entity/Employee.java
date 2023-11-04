package com.prasanth.assignment.entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "EMPLOYEES")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotEmpty(message = "Employee ID is required")
	@Column(name = "EMPLOYEE_ID")
	private String employeeId;

	@NotEmpty(message = "First Name is required")
	@Column(name = "FIRST_NAME")
	private String firstName;

	@NotEmpty(message = "Last Name is required")
	@Column(name = "LAST_NAME")
	private String lastName;

	@Email(message = "Invalid Email")
	@NotEmpty(message = "Email is required")
	@Column(name = "EMAIL")
	private String email;

	@NotNull(message = "Date of Joining is required")
	@Column(name = "DATE_OF_JOINING")
	private Date dateOfJoining;

	@NotNull(message = "Salary is required")
	@Column(name = "SALARY")
	private String salary;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "EMP_ID", referencedColumnName = "ID")
	private Set<EmployeeMobileNumbers> mobileNumbers;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(final String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(final Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(final String salary) {
		this.salary = salary;
	}

	public Set<EmployeeMobileNumbers> getMobileNumbers() {
		return mobileNumbers;
	}

	public void setMobileNumbers(final Set<EmployeeMobileNumbers> mobileNumbers) {
		this.mobileNumbers = mobileNumbers;
	}
}
