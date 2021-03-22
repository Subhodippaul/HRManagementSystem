package com.nagarro.hrmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nagarro.hrmanager.entity.Employee;

/**
 * 
 * @author subhodippaul
 *
 *this is interface of the EmployeeServiceImplements
 */
@Service
public interface EmployeeService {
	
	/**
	 * method to save employee
	 */
	public void saveEmployee(Employee employee);

	/**
	 * methdo to all employee
	 */
	public List<Employee> allEmployee();
	
	/**
	 * method to delete employee
	 */
	public void deleteEmployee(Employee employee);
	
	/**
	 * method to get employee by ID
	 */
	public Employee getEmployeeById(long id);
	
	/**
	 * method of delete employee by ID
	 */
	public Employee deleteEmployeeById(long id);
	
	/**
	 * method of the list employee
	 */
	List<Employee> listEmployee();

}
