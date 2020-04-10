package com.user.mareez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.user.mareez.dao.UserDao;
import com.user.mareez.model.User;

@Controller
public class SignUpController {

	@Autowired
	UserDao userDao;

	/**
	 * Create new signUpForm object for empty from
	 * 
	 * @return
	 */
	@ModelAttribute("user")
	public User setSignUpForm() {
		return new User();
	}

	/**
	 * Method to show the initial HTML form
	 * 
	 * @return
	 */
	@GetMapping("/showSignUpForm")
	public String showForm() {
		return "signup-form";
	}

	/**
	 * Save User sign up form
	 * 
	 * @param signUpForm
	 * @param model
	 * @return
	 */
	@PostMapping("/saveSignUpForm")
	public String saveUser(@ModelAttribute("user") User user, Model model) {

		// Implement business logic to save user details into a database
		// .....

		User check = userDao.findByEmail(user.getEmail());
		
		if(check != null) {
			model.addAttribute("alreadyExist", "This email is already registered!");
			
			return "signup-form";
		}
		
		int result = userDao.insertNewUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),
				user.getGender(), user.getDob(), user.getAddress(), user.getPostal());

		if (result > 0) {
	

			model.addAttribute("message", "Thank you, " + user.getFirstName() +" for registering with us. Please wait while we approve your request!");
//		model.addAttribute("user", user);

			return "signup-success";
		}
		return "showSignUpForm";
	}
}
