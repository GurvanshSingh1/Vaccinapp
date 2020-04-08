package com.user.mareez.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.user.mareez.dao.UserDao;
import com.user.mareez.model.ClinicInfo;
import com.user.mareez.model.Enquiry;
import com.user.mareez.model.User;
import com.user.mareez.model.UserVaccinationInfo;

@SessionAttributes("user")
@Controller
public class UserEnquiryController {

	@Autowired
	UserDao userDao;
	
	@ModelAttribute("Enquiry")
	public Enquiry enquiry() {
		return new Enquiry();
	}
	
	@ModelAttribute("UserVaccinationInfo")
	public UserVaccinationInfo userVaccinationInfo() {
		return new UserVaccinationInfo();
	}
	
	@ModelAttribute("ClinicInfo")
	public ClinicInfo clinicInfo() {
		return new ClinicInfo();
	}
	
	@PostMapping("/addEnquiry")
	public String postNews(HttpSession session,
			@ModelAttribute("Enquiry") Enquiry enquiry, Model model) {
		User user = (User) session.getAttribute("user");

		int result = userDao.insertEnquiry(enquiry.getEnquiry(), user.getFirstName(), user.getEmail());

		if (result > 0) {
			model.addAttribute("approvedEnquiry", "Sent!");
		}

		model.addAttribute("user", user);
		model.addAttribute("userName", user.getFirstName());
		model.addAttribute("message", "Welcome, " + user.getFirstName() + "!");
		//Generate notifications
		String userNotifications = "You have no notifications";
		List<Enquiry> userEnq = userDao.findEnquiryByUser(user.getEmail());
		int count = 0;
		for(int i =0; i < userEnq.size(); i++) {
			if(userEnq.get(i).getIsReplied() == 1) {
				count++;
			}
		};
		
		if(count > 0) {
			userNotifications = "\nYou have "+count+ " Enquiry reply(s)";
		}
		model.addAttribute("userNotifications",userNotifications);
		return "login-success";
	}
	
	@GetMapping("/viewEnquiry")
	public String viewEnquiry(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			List<Enquiry> enquiry = userDao.findEnquiryByUser(user.getEmail());
			model.addAttribute("enquiry", enquiry);
		}
		return "view-all-enquiry";
	}	
}
