package com.user.mareez.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.user.mareez.model.ClinicInfo;
import com.user.mareez.model.DailyNews;
import com.user.mareez.model.Enquiry;
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
	
	@GetMapping("/updateReply")
	public String updateReply(@RequestParam("enquiryId") String enquiryId, @RequestParam("response") String response, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			userDao.updateEnquiryReply(Integer.parseInt(enquiryId), response);
			List<Enquiry> enquiry = userDao.findEnquiryByUser();
			model.addAttribute("enquiry", enquiry);
		}
		return "view-all-enquiry-admin";
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
	
	@PostMapping("/postNews")
	public String postNews(HttpSession session,
			@ModelAttribute("DailyNews") DailyNews dailyNews, Model model) {
		User user = (User) session.getAttribute("user");
		
		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());

		int result = userDao.insertDailyNews(dailyNews.getNews(), user.getFirstName(), date);

		if (result > 0) {
			model.addAttribute("approvedNews", "News Posted!");
		}

		model.addAttribute("user", user);
		model.addAttribute("userName", user.getFirstName());
		model.addAttribute("message", "Welcome, " + user.getFirstName() + "!");
		return "login-success-admin";
	}
	
	@PostMapping("/addNewClinic")
	public String addNewClinic(HttpSession session,
			@ModelAttribute("ClinicInfo") ClinicInfo clinicInfo, Model model) {
		User user = (User) session.getAttribute("user");
		
		int result = userDao.insertNewClinic(clinicInfo.getClinicName(), clinicInfo.getClinicAddress(), clinicInfo.getClinicContact(), clinicInfo.getClinicEmail());

		if (result > 0) {
			model.addAttribute("approvedClinic", clinicInfo.getClinicName() + " is added!");
		}

		model.addAttribute("user", user);
		model.addAttribute("userName", user.getFirstName());
		model.addAttribute("message", "Welcome, " + user.getFirstName() + "!");
		return "login-success-admin";
	}
	
	@GetMapping("/viewAdminEnquiry")
	public String viewEnquiry(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			List<Enquiry> enquiry = userDao.findEnquiryByUser();
			model.addAttribute("enquiry", enquiry);
		}
		return "view-all-enquiry-admin";
	}
	
	@GetMapping("/viewClinics")
	public String viewClinic(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			List<ClinicInfo> clinicInfo = userDao.findClinics();
			model.addAttribute("clinicInfo", clinicInfo);
		}
		return "view-all-clinics-admin";
	}
	
	@GetMapping("/deleteClinic")
	public String deleteClinic(@RequestParam("clinicId") String clinicId, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			userDao.deleteClinic(Integer.parseInt(clinicId));
			List<ClinicInfo> clinicInfo = userDao.findClinics();
			model.addAttribute("clinicInfo", clinicInfo);
		}
		return "view-all-clinics-admin";
	}
}
