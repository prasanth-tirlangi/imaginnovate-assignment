package com.prasanth.assignment.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prasanth.assignment.entity.Employee;
import com.prasanth.assignment.model.EmployeeTaxStatement;
import com.prasanth.assignment.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeService(final EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public Employee getEmployeeById(Integer id) {
		Optional<Employee> emp = employeeRepository.findById(id);
		if (emp.isPresent()) {
			return emp.get();
		}
		return null;
	}

	public Employee saveOrUpdate(Employee employee) {
		return employeeRepository.save(employee);
	}

	public EmployeeTaxStatement calculateTaxAndCess(Integer id) {
		Employee employee = new Employee();
		Optional<Employee> emp = employeeRepository.findById(id);
		if (emp.isPresent()) {
			employee = emp.get();
		} else {
			return null;
		}
		EmployeeTaxStatement taxStatement = new EmployeeTaxStatement();

		long monthsWorked = calculateEffectiveYearlySalary(employee.getDateOfJoining());
		double yearlySalary = (Double.valueOf(employee.getSalary())) * monthsWorked;
		double salaryToRemove = salaryForMissingDays(employee.getDateOfJoining(), employee.getSalary());
		yearlySalary = yearlySalary - salaryToRemove;
		double taxAmount = 0;
		double cessAmount = 0;

		if (yearlySalary <= 250000) {
			taxAmount = 0;
		} else if (yearlySalary <= 500000) {
			taxAmount = (yearlySalary - 250000) * 0.05;
		} else if (yearlySalary <= 1000000) {
			taxAmount = (250000 * 0.05) + ((yearlySalary - 500000) * 0.10);
		} else {
			taxAmount = (250000 * 0.05) + (500000 * 0.10) + ((yearlySalary - 1000000) * 0.20);
		}

		if (yearlySalary > 2500000) {
			double exceededAmountForCess = yearlySalary - 2500000;
			cessAmount = exceededAmountForCess * 0.02;
		}

		taxStatement.setEmployeeId(employee.getEmployeeId());
		taxStatement.setFirstName(employee.getFirstName());
		taxStatement.setLastName(employee.getLastName());
		taxStatement.setYearlySalary(yearlySalary);
		taxStatement.setTaxAmount(taxAmount);
		taxStatement.setCessAmount(cessAmount);

		return taxStatement;
	}

	private double salaryForMissingDays(Date doj, String monthlySalary) {
		LocalDate dateOfJoining = LocalDate.ofInstant(doj.toInstant(), ZoneId.systemDefault());
		LocalDate firstDayOfMonth = dateOfJoining.with(TemporalAdjusters.firstDayOfMonth());

		int remainingDays = dateOfJoining.getDayOfMonth() - firstDayOfMonth.getDayOfMonth();
		double daySalary = Double.valueOf(monthlySalary) / 30;
		return daySalary * remainingDays;
	}

	private long calculateEffectiveYearlySalary(Date doj) {
		LocalDate currentDate = LocalDate.now();
		LocalDate startOfFinancialYear = LocalDate.of(currentDate.getYear(), 4, 1);
		LocalDate endOfFinancialYear = LocalDate.of(currentDate.getYear() + 1, 3, 31);

		LocalDate dateOfJoining = LocalDate.ofInstant(doj.toInstant(), ZoneId.systemDefault());
		int monthsWorked = calculateMonthsWorked(dateOfJoining, startOfFinancialYear, endOfFinancialYear);
		return monthsWorked + 1;
	}

	private int calculateMonthsWorked(LocalDate startDate, LocalDate periodStart, LocalDate periodEnd) {
		if (startDate.isBefore(periodStart)) {
			startDate = periodStart;
		}

		Period period = Period.between(startDate, periodEnd);
		int months = period.getYears() * 12 + period.getMonths();

		return Math.max(0, months);
	}

}
