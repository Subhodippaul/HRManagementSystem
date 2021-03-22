package com.nagarro.hrmanager.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lowagie.text.DocumentException;
import com.nagarro.hrmanager.createpdf.CreatePDF;
import com.nagarro.hrmanager.entity.Employee;
import com.nagarro.hrmanager.service.EmployeeService;

/**
 * 
 * @author subhodippaul
 *
 *This method is used to controll the employee
 */
@Controller
public class EmployeeController {

	/**
	 * create the object of the EmployeeService
	 */
	@Autowired
	public EmployeeService employeeService;

	/**
	 * get the employee list page 
	 */
	@GetMapping("/employeeList")
	
	/**
	 * method to show the all employee list
	 */
	public String Employee(Model model) {
		List<Employee> employee = this.employeeService.allEmployee();
		model.addAttribute("employeeList",employee);
		return "EmployeeList";
	}

	/**
	 * get the form of the upload new employee
	 */
	@GetMapping("/showFormNewEmployee")
	
	/**
	 * method for upload the new employee
	 */
	public String employeeForm() {
		return "AddNewEmployee";
	}
	
	/**
	 * get the form of the update employee
	 */
	@GetMapping("/showFormForUpdate/{id}")
	
	/**
	 * method of update employee
	 */
	public String updateEmployeeForm(@PathVariable("id") long id,Model model) {
		/**
		 * get the employee by Id and store the variable employee
		 */
		Employee employee = this.employeeService.getEmployeeById(id);
		model.addAttribute("employeeData", employee);
		/**
		 * return the UpdateEmployeeDetails form page 
		 */
		return "UpdateEmployeeDetails";
	}

	
	/**
	 * post mapping to the saveEmployee
	 */
	@PostMapping("/saveEmployee")
	
	/**
	 * method to save the employee
	 */
	public String saveEmployee(@ModelAttribute("employeeData") Employee employeeData) {
		/**
		 * print the saving to the Console
		 */
		System.out.println("saveing");
		
		/**
		 * calling the saveEmployee method from the employeeService
		 */
		
		this.employeeService.saveEmployee(employeeData);
		
		/**
		 * after save the form it will redirect to the employee list page 
		 */
		return "redirect:/employeeList";
	}


	/**
	 * get mapping to the delete employee
	 */
	@GetMapping("/deleteEmployee/{id}")
	
	/**
	 * method to delete the perticular employee 
	 */
	public String deleteEmployee(@PathVariable("id") long id,Model model) {
		/**
		 * print the message to the console
		 */
		System.out.println("deleting");
		
		/**
		 * call the method deleteEmployeeById from employeeService
		 */
		this.employeeService.deleteEmployeeById(id);
		
		/**
		 * after delete the employee it will redirect to the employee list
		 */
		return "redirect:/employeeList";
	}

	/**
	 * get mapping of the exportToPDF method
	 */
	@GetMapping("/users/export/pdf")
	
	/**
	 *method to create the pdf 
	 */
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		
		/**
		 * set the time and date format
		 */
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		
		/**
		 * store the date to the currentDateTime variable
		 */
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=employeeList_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<Employee> listEmployee = employeeService.listEmployee();

		CreatePDF exporter = new CreatePDF(listEmployee);
		exporter.export(response);

	}


}
