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
		if (user != null) {
			userDao.approveUserByEmail(userEmail);
			List<User> userUnapproved = userDao.findUnapprovedUsers();
			model.addAttribute("userUnapproved", userUnapproved);
		}

		return "view-all-pendingApprovals";
	}

	@GetMapping("/viewAllAdminRecords")
	public String viewAllAdminRecords(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			List<AdminVaccinationInfo> adminVaccinationInfo = userDao.findAdminVaccination();
			model.addAttribute("adminVaccinationInfo", adminVaccinationInfo);
		}
		return "view-all-records-admin";
	}

	@GetMapping("/deleteAdminVaccination")
	public String deleteUserVaccination(@RequestParam("vaccinId") String vaccinId, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			userDao.deleteAdminVaccination(Integer.parseInt(vaccinId));
			List<AdminVaccinationInfo> adminVaccinationInfo = userDao.findAdminVaccination();
			model.addAttribute("adminVaccinationInfo", adminVaccinationInfo);
		}
		return "view-all-records-admin";
	}

	@PostMapping("/addNewAdminVaccination")
	public String saveUserVaccination(HttpSession session,
			@ModelAttribute("AdminVaccinationInfo") AdminVaccinationInfo adminVaccinationInfo, Model model) {

		int result = userDao.insertAdminUserVaccination(adminVaccinationInfo.getVaccinType(),
				adminVaccinationInfo.getNotes(), adminVaccinationInfo.getVaccinEffective());

		if (result > 0) {
			model.addAttribute("approved", adminVaccinationInfo.getVaccinType() + " is added!");
		}
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		model.addAttribute("userName", user.getFirstName());
		model.addAttribute("message", "Welcome, " + user.getFirstName() + "!");
		return "login-success-admin";
	}
}
