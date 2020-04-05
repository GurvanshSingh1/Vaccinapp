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

import com.user.mareez.dao.AdminDao;
import com.user.mareez.dao.UserDao;
import com.user.mareez.model.AdminVaccinationInfo;
import com.user.mareez.model.ClinicInfo;
import com.user.mareez.model.Enquiry;
import com.user.mareez.model.User;
import com.user.mareez.model.UserVaccinationInfo;

@SessionAttributes("user")
@Controller
public class UserVaccinationController {

	@Autowired
	UserDao userDao;
	
	@Autowired
	AdminDao adminDao;

	@ModelAttribute("UserVaccinationInfo")
	public UserVaccinationInfo userVaccinationInfo() {
		return new UserVaccinationInfo();
	}
	
	@ModelAttribute("Enquiry")
	public Enquiry enquiry() {
		return new Enquiry();
	}
	
	@ModelAttribute("ClinicInfo")
	public ClinicInfo clinicInfo() {
		return new ClinicInfo();
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
		List <AdminVaccinationInfo> adminVaccinationInfo = adminDao.findAdminVaccination();
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
	
}
