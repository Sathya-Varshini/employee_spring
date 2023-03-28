package com.codedecode.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.custom.exception.BusinessException;
import com.codedecode.demo.entity.Employee;
import com.codedecode.demo.repos.EmployeeCrudRepo;

@Service
public class EmployeeService implements EmployeeServiceInterface{
	
	@Autowired
	private EmployeeCrudRepo crudRepo;

	@Override
	public Employee addEmployee(Employee employee) throws BusinessException {

		if(employee.getName().isEmpty() || employee.getName().length() == 0 ) {
			throw new BusinessException("601","Please send proper name, It blank");
		}
		try {
			Employee savedEmployee = crudRepo.save(employee);
			return savedEmployee;
		}catch (IllegalArgumentException e) {
			throw new BusinessException("602","given employee is null" + e.getMessage());
		}catch (Exception e) {
			throw new BusinessException("603","Something went wrong in Service layer while saving the employee" + e.getMessage());
		}


	}

	@Override
	public List<Employee> getAllEmployees() throws BusinessException {
		try{
			List<Employee> empList = null;
			empList = crudRepo.findAll();
			if(empList.isEmpty())
				throw new BusinessException("604", "Hey list completely empty, we have nothing to return ");//this catch 
			return empList;
		}
		catch (Exception e) {
			throw new BusinessException("605","Something went wrong in Service layer while fetching all employees " + e.getMessage());
		}
		
	}
		
	

	@Override
	public Employee getEmpById(Long empidL)  {
		try {
			return crudRepo.findById(empidL).get();
		}
		catch (java.util.NoSuchElementException e) {
			throw new BusinessException("607","given employee id doesnot exist in DB" + e.getMessage());
		}catch (Exception e) {
			throw new BusinessException("609","Something went wrong in Service layer while fetching all employees" + e.getMessage());
		}
		
	}

	@Override
	public void deleteEmpById(Long empidL) {
		try {
			crudRepo.deleteById(empidL);
		}catch (Exception e) {
			throw new BusinessException("610","Something went wrong in Service layer while fetching all employees" + e.getMessage());
		}
		
	}

}
