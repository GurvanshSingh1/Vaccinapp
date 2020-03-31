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
import com.user.mareez.model.AdminVaccinationInfo;
import com.user.mareez.model.User;


@SessionAttributes("user")
@Controller
public class AddAdminVaccinationController {

	@Autowired
	UserDao userDao;
	
	@ModelAttribute("AdminVaccinationInfo")
	public AdminVaccinationInfo adminVaccinationInfo() {
		return new AdminVaccinationInfo();
	}


	@GetMapping("/viewPendingApprovals")
	public String viewAllRecords(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			List<User> userUnapproved = userDao.findUnapprovedUsers();
			model.addAttribute("userUnapproved", userUnapproved);
		}
		return "view-all-pendingApprovals";
	}
	
	@GetMapping("/approveUser")
	public String approveUser(@RequestParam("userEmail") String userEmail, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user != null) {
		userDao.approveUserByEmail(userEmail);
			List<User> userUnapproved = userDao.findUnapprovedUsers();
			model.addAttribute("userUnapproved", userUnapproved);
		}

		return "view-all-pendingApprovals";
	}
	
	@PostMapping("/addNewAdminVaccination")
	public String saveUserVaccination(HttpSession session,
			@ModelAttribute("AdminVaccinationInfo") AdminVaccinationInfo adminVaccinationInfo, Model model) {

		// Implement business logic to save user details into a database
		// .....

		int result = userDao.insertAdminUserVaccination(adminVaccinationInfo.getVaccinType(), adminVaccinationInfo.getNotes(), adminVaccinationInfo.getVaccinEffective());

		if (result > 0) {

			model.addAttribute("approved", adminVaccinationInfo.getVaccinType() + " is added!");
//		model.addAttribute("user", user);

		}


		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		model.addAttribute("userName", user.getFirstName());
		model.addAttribute("message", "Welcome, " + user.getFirstName() + "!");
		return "login-success-admin";

	}


}
