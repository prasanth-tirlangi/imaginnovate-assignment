package com.prasanth.assignment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prasanth.assignment.entity.Employee;
import com.prasanth.assignment.model.EmployeeTaxStatement;
import com.prasanth.assignment.service.EmployeeService;

@RestController
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(final EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/employee/{id}")
	private Employee getStudent(@PathVariable("id") int id) {
		return employeeService.getEmployeeById(id);
	}

	@PostMapping("/employee")
	private ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
		Employee savedEmployee = employeeService.saveOrUpdate(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
	}

	@GetMapping("/employee/tax-statement/{id}")
	private ResponseEntity<?> getEmployeeTax(@PathVariable("id") int id) {
		EmployeeTaxStatement taxAndCess = employeeService.calculateTaxAndCess(id);
		return ResponseEntity.status(HttpStatus.OK).body(taxAndCess);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		return ResponseEntity.badRequest().body("Validation Error: " + ex.getBindingResult().getAllErrors());
	}
}