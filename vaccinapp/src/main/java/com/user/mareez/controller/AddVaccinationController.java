package com.user.mareez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.user.mareez.dao.UserDao;
import com.user.mareez.model.UserVaccinationInfo;

@Controller
@SessionAttributes("user")
public class AddVaccinationController {
	
	@Autowired
	UserDao userDao;
	
	@GetMapping("/addNewUserVaccination")
	public String showForm() {
		return "login-success";
	}
	
	@ModelAttribute("UserVaccinationInfo")
	public UserVaccinationInfo userVaccinationInfo() {
		return new UserVaccinationInfo();
	}

	@PostMapping("/addNewUserVaccination")
	public String saveUser(@ModelAttribute("UserVaccinationInfo") UserVaccinationInfo userVaccinationInfo, Model model) {

		// Implement business logic to save user details into a database
		// .....

		int result = userDao.insertNewUserVaccination(userVaccinationInfo.getFname(), userVaccinationInfo.getVaccinType(), userVaccinationInfo.getVaccinDate(), userVaccinationInfo.getNotes());

		if (result > 0) {
	

			model.addAttribute("approved", userVaccinationInfo.getVaccinType() + " is added!");
//		model.addAttribute("user", user);

			
		}
		return "login-success";

	}
}
