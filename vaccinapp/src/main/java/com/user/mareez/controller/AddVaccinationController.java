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
import com.user.mareez.model.DailyNews;
import com.user.mareez.model.Enquiry;
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
	
	@ModelAttribute("Enquiry")
	public Enquiry enquiry() {
		return new Enquiry();
	}

	@PostMapping("/addNewUserVaccination")
	public String saveUserVaccination(HttpSession session,
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
		List <AdminVaccinationInfo> adminVaccinationInfo = userDao.findAdminVaccination();
		model.addAttribute("adminVaccinationInfo", adminVaccinationInfo);
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
	
	@GetMapping("/viewDailyNews")
	public String viewDailyNews(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			List<DailyNews> dailyNews = userDao.findDailyNews();
			model.addAttribute("dailyNews", dailyNews);
		}
		return "view-all-news";
	}

	@GetMapping("/deleteUserVaccination")
	public String deleteUserVaccination(@RequestParam("vaccinId") String vaccinId, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user != null) {
		userDao.deleteUserVaccination(Integer.parseInt(vaccinId));
		List<UserVaccinationInfo> userVaccinationInfo = userDao.findVaccinationByUser(user.getFirstName());
		model.addAttribute("userVaccinationInfo", userVaccinationInfo);
		}

		return "view-all-records";
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
