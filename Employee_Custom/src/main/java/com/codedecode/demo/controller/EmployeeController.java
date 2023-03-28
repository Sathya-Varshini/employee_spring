package com.codedecode.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.custom.exception.BusinessException;
import com.codedecode.demo.custom.exception.ControllerException;
import com.codedecode.demo.entity.Employee;
import com.codedecode.demo.service.EmployeeServiceInterface;

@RestController
@RequestMapping("/code")
public class EmployeeController {

	@Autowired
	private EmployeeServiceInterface employeeServiceInterface;
	
	 private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	@PostMapping("/save")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee){
		try {
			Employee employeeSaved = employeeServiceInterface.addEmployee(employee);
			logger.info("employee saved successfully");
			return new ResponseEntity<Employee>(employeeSaved, HttpStatus.CREATED);
		}catch (BusinessException e) {
			 logger.error("Give some values", e); //stacktrace
			return new ResponseEntity<Object>(e.getErrorMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllEmployees(){
		try {
			List<Employee> listOfAllEmps = employeeServiceInterface.getAllEmployees();
			return new ResponseEntity<List<Employee>>(listOfAllEmps, HttpStatus.OK);}
	   catch (BusinessException e) {
			return new ResponseEntity<Object>(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/emp/{empid}")
	public ResponseEntity<?> getEmpById(@PathVariable("empid") Long empidL){
		try {
			Employee empRetrieved = employeeServiceInterface.getEmpById(empidL);
			return new ResponseEntity<Employee>(empRetrieved, HttpStatus.OK);
		}catch (BusinessException e) {
			return new ResponseEntity<Object>(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			ControllerException ce = new ControllerException("612","Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{empid}")
	public ResponseEntity<?> deleteEmpById(@PathVariable("empid") Long empidL){
		try {
		employeeServiceInterface.deleteEmpById(empidL);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);}
		catch (BusinessException e) {
			return new ResponseEntity<Object>(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
		Employee employeeSaved = employeeServiceInterface.addEmployee(employee);
		return new ResponseEntity<Employee>(employeeSaved, HttpStatus.CREATED);
	}

	
	
}
