package com.user.mareez.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.user.mareez.dao.UserDao;
import com.user.mareez.model.User;


@SessionAttributes("user")
@Controller
public class AddAdminVaccinationController {

	@Autowired
	UserDao userDao;


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


}
