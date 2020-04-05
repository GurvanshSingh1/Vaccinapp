package com.user.mareez.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.user.mareez.dao.AdminDao;
import com.user.mareez.dao.UserDao;
import com.user.mareez.model.AdminVaccinationInfo;
import com.user.mareez.model.ClinicInfo;
import com.user.mareez.model.DailyNews;
import com.user.mareez.model.User;

@SessionAttributes("user")
@Controller
public class AdminApprovalController {

	@Autowired
	UserDao userDao;
	
	@Autowired
	AdminDao adminDao;

	@ModelAttribute("AdminVaccinationInfo")
	public AdminVaccinationInfo adminVaccinationInfo() {
		return new AdminVaccinationInfo();
	}

	@ModelAttribute("DailyNews")
	public DailyNews dailyNews() {
		return new DailyNews();
	}
	
	@ModelAttribute("ClinicInfo")
	public ClinicInfo clinicInfo() {
		return new ClinicInfo();
	}
	
	@GetMapping("/viewPendingApprovals")
	public String viewAllRecords(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			List<User> userUnapproved = adminDao.findUnapprovedUsers();
			model.addAttribute("userUnapproved", userUnapproved);
		}
		return "view-all-pendingApprovals";
	}

	@GetMapping("/approveUser")
	public String approveUser(@RequestParam("userEmail") String userEmail, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			adminDao.approveUserByEmail(userEmail);
			List<User> userUnapproved = adminDao.findUnapprovedUsers();
			model.addAttribute("userUnapproved", userUnapproved);
		}

		return "view-all-pendingApprovals";
	}
	
	
}
