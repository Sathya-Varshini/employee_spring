package com.codedecode.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.custom.exception.BusinessException;
import com.codedecode.demo.entity.Employee;
import com.codedecode.demo.service.EmployeeServiceInterface;

@RestController
@RequestMapping("/code")
public class EmployeeController {

	@Autowired
	private EmployeeServiceInterface employeeServiceInterface;

	@PostMapping("/save")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee){
			Employee employeeSaved = employeeServiceInterface.addEmployee(employee);
			return new ResponseEntity<Employee>(employeeSaved, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployees() {

		List<Employee> listOfAllEmps = employeeServiceInterface.getAllEmployees();
		return new ResponseEntity<List<Employee>>(listOfAllEmps, HttpStatus.OK);
	}

	@GetMapping("/emp/{empid}")
	public ResponseEntity<?> getEmpById(@PathVariable("empid") Long empidL) {
		Employee empRetrieved = employeeServiceInterface.getEmpById(empidL);
		return new ResponseEntity<Employee>(empRetrieved, HttpStatus.OK);
		
	}

	@DeleteMapping("/delete/{empid}")
	public ResponseEntity<Void> deleteEmpById(@PathVariable("empid") Long empidL) {

		employeeServiceInterface.deleteEmpById(empidL);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	@PutMapping("/update")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		Employee employeeSaved = employeeServiceInterface.addEmployee(employee);
		return new ResponseEntity<Employee>(employeeSaved, HttpStatus.CREATED);
	}
	 @ExceptionHandler(BusinessException.class)
	    public ResponseEntity<String> HandleException(BusinessException e) {
		 return new ResponseEntity<String>(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
	  }
}