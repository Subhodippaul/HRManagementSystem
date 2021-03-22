package com.nagarro.hrmanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.hrmanager.dao.UserLoginRepository;
import com.nagarro.hrmanager.entity.Login;
/**
 * 
 * @author subhodippaul
 * this class is to declare the service of the login form
 */

@Service
public class LoginServiceImplementation implements LoginService  {

	/**
	 * create the object of the UserLoginRepository
	 */
	@Autowired
	private UserLoginRepository loginRepository;

	/**
	 * this method is used to check the login credentials of the user
	 */
	@Override
	public boolean checkLogin(int userId, String userPassword) {
		/**
		 * fetch the perticular user by userId and store the find variable
		 */
		Optional<Login> find =loginRepository.findById(userId);

		/**
		 * initially set login variable to null
		 */
		Login login = null;

		/**
		 * check if the user os present or not 
		 */

		if(find.isPresent()) {

			/**
			 * if the user is present to the database then fetch all the details of that user and 
			 * store to the login variable 
			 */
			login = find.get();
			
			/**
			 * check if the user given password match with the database or not  
			 */
			if(login.getPassword().equals(userPassword)) {
				/**
				 * if match the user password then return true
				 */
				return true;

			}

		}
		/**
		 * if the user is not present the return false
		 */
		return false;


	}

}
