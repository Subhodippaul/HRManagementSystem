package com.nagarro.hrmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.nagarro.hrmanager.entity.Employee;

/**
 * 
 * @author subhodippaul
 *
 *this is a interface of the EmployeeRepository extends to the  JpaRepository
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
