package com.user.mareez.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.user.mareez.dao.UserDao;
import com.user.mareez.model.User;
import com.user.mareez.model.UserVaccinationInfo;

@SessionAttributes("user")
@Controller
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
	public String saveUser(HttpSession session,
			@ModelAttribute("UserVaccinationInfo") UserVaccinationInfo userVaccinationInfo, Model model) {

		// Implement business logic to save user details into a database
		// .....

		int result = userDao.insertNewUserVaccination(userVaccinationInfo.getFname(),
				userVaccinationInfo.getVaccinType(), userVaccinationInfo.getVaccinDate(),
				userVaccinationInfo.getNotes());

		if (result > 0) {

			model.addAttribute("approved", userVaccinationInfo.getVaccinType() + " is added!");
//		model.addAttribute("user", user);

		}
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		model.addAttribute("userName", user.getFirstName());
		model.addAttribute("message", "Welcome, " + user.getFirstName() + "!");
		return "login-success";

	}

	@GetMapping("/viewAllRecords")
	public String viewAllRecords(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			List<UserVaccinationInfo> userVaccinationInfo = userDao.findVaccinationByUser(user.getFirstName());
			model.addAttribute("userVaccinationInfo", userVaccinationInfo);
		}
		return "view-all-records";
	}

	@GetMapping("/deleteUserVaccination")
	public String deleteUserVaccination(@RequestParam("vaccinType") String vaccinType, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user != null) {
		userDao.deleteUserVaccination(user.getFirstName(), vaccinType);
		List<UserVaccinationInfo> userVaccinationInfo = userDao.findVaccinationByUser(user.getFirstName());
		model.addAttribute("userVaccinationInfo", userVaccinationInfo);
		}

		return "view-all-records";
	}
}
