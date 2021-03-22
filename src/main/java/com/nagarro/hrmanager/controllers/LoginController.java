package com.nagarro.hrmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.hrmanager.service.LoginService;

/**
 * 
 * @author subhodippaul
 *
 *This class is used for Controll the login system
 */
@Controller
public class LoginController {
	
	/**
	 * to get the Login form page
	 */
	@GetMapping("/Login")
	public String Login() {
		/**
		 * print in the Console if login form is open
		 */
		System.out.println("login page");
		
		/**
		 * return to the LoginForm page
		 */
		return "LoginForm";
	}
	
	/**
	 * create the object of LoginService
	 */
	@Autowired
	public LoginService loginService;
	
	/**
	 * after submission the login form it will redirect to the Employee List page
	 */
	@PostMapping("/employeeList")
	
	/**
	 * this is a method to check the user for the login
	 */
	public String checkUser(@RequestParam("userId") int userId,
			@RequestParam("password") String userPassword) {

		/**
		 * create the object of ModelAndView
		 */
		ModelAndView modelView = new ModelAndView();
		/**
		 * add username
		 */
		modelView.addObject("userId",userId);
		/**
		 * add user password
		 */
		modelView.addObject("password",userPassword);
		/**
		 * store the check login method into the userExists variable
		 */
		boolean userExists = loginService.checkLogin(userId, userPassword);
		
		/**
		 * check if the user Existis
		 */
		if(userExists) {
			
			/**
			 * if the user credentials match to the database then redirect to employee List page
			 */
			return "redirect:/employeeList";
		}
			/**
			 * if user enter the wrong credential it will stay in the login form 
			 */
			return "LoginForm";


	}
	
}
