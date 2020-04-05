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
import com.user.mareez.model.Enquiry;
import com.user.mareez.model.User;

@SessionAttributes("user")
@Controller
public class AdminEnquiryController {

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
	
	@GetMapping("/updateReply")
	public String updateReply(@RequestParam("enquiryId") String enquiryId, @RequestParam("response") String response, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			adminDao.updateEnquiryReply(Integer.parseInt(enquiryId), response);
			List<Enquiry> enquiry = adminDao.findEnquiryByUser();
			model.addAttribute("enquiry", enquiry);
		}
		return "view-all-enquiry-admin";
	}
		
	@GetMapping("/viewAdminEnquiry")
	public String viewEnquiry(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			List<Enquiry> enquiry = adminDao.findEnquiryByUser();
			model.addAttribute("enquiry", enquiry);
		}
		return "view-all-enquiry-admin";
	}		
}
