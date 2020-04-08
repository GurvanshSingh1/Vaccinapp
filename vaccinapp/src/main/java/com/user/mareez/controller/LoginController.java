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

import com.user.mareez.dao.AdminDao;
import com.user.mareez.dao.UserDao;
import com.user.mareez.model.AdminVaccinationInfo;
import com.user.mareez.model.ClinicInfo;
import com.user.mareez.model.DailyNews;
import com.user.mareez.model.Enquiry;
import com.user.mareez.model.LoginInfo;
import com.user.mareez.model.User;
import com.user.mareez.model.UserVaccinationInfo;

@Controller
@SessionAttributes("user")
public class LoginController {

	@Autowired
	UserDao userDao;
	
	@Autowired
	AdminDao adminDao;

	/**
	 * Create new signUpForm object for empty from
	 * 
	 * @return
	 */
	@ModelAttribute("loginInfo")
	public LoginInfo loginForm() {
		return new LoginInfo();
	}

	@ModelAttribute("UserVaccinationInfo")
	public UserVaccinationInfo userVaccinationInfo() {
		return new UserVaccinationInfo();
	}

	@ModelAttribute("AdminVaccinationInfo")
	public AdminVaccinationInfo adminVaccinationInfo() {
		return new AdminVaccinationInfo();
	}

	@ModelAttribute("DailyNews")
	public DailyNews dailyNews() {
		return new DailyNews();
	}

	@ModelAttribute("Enquiry")
	public Enquiry enquiry() {
		return new Enquiry();
	}

	@ModelAttribute("ClinicInfo")
	public ClinicInfo clinicInfo() {
		return new ClinicInfo();
	}

	/**
	 * Method to show the initial HTML form
	 * 
	 * @return
	 */
	
	@GetMapping("/index")
	public String handler(HttpSession session, Model model) {
	    User user = (User) session.getAttribute("user");
	    if(user != null) {
	    	session.invalidate();
	    }
		return "index";
	}
	
	@GetMapping("/login")
	public String login(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			List<AdminVaccinationInfo> adminVaccinationInfo = adminDao.findAdminVaccination();
			model.addAttribute("adminVaccinationInfo", adminVaccinationInfo);
			model.addAttribute("user", user);
			model.addAttribute("userName", user.getFirstName());
			model.addAttribute("message", "Welcome, " + user.getFirstName() + "!");
			if (user.getUserType().contentEquals("USER")) {
				List<ClinicInfo> clinicInfo = userDao.findClinics();
				model.addAttribute("clinicInfo", clinicInfo);
				List<DailyNews> dailyNews = userDao.findDailyNews();
				model.addAttribute("todayNews", "NEWS FLASH: "+ dailyNews.get(0).getNews() +" - " + dailyNews.get(0).getPostedBy());
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
			} else {
				//Generate notifications
				String adminNotifications = "You have no notifications";
				List<User> userInfo = adminDao.findUnapprovedUsers();
				if(userInfo.size() > 0) {
					adminNotifications = "\nYou have "+userInfo.size()+ " pending user approval(s)";
				}
				List<Enquiry> userEnq = adminDao.findEnquiryByUser();
				int count = 0;
				for(int i=0; i< userEnq.size(); i++) {
					if(userEnq.get(i).getResponse().equalsIgnoreCase("Waiting Response!")) {
						count++;
					}
				}
				if(count > 0) {
					adminNotifications = "\nYou have "+count+ " pending enquiry(s)";
				}
				model.addAttribute("adminNotifications", adminNotifications);
				return "login-success-admin";
			}
		}
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			session.invalidate();
		}
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("loginInfo") LoginInfo loginInfo, Model model) {
		User user = userDao.findByEmail(loginInfo.getEmail());
		List<AdminVaccinationInfo> adminVaccinationInfo = adminDao.findAdminVaccination();
		model.addAttribute("messageInvalid", "User does not exist or it is not approved by the admin yet!");
		
		

		if (user != null && user.getPassword().equals(loginInfo.getPassword()) && (user.getIsApproved() == 1)) {
			model.addAttribute("user", user);
			model.addAttribute("adminVaccinationInfo", adminVaccinationInfo);
			model.addAttribute("userName", user.getFirstName());
			model.addAttribute("message", "Welcome, " + user.getFirstName() + "!");
			if (user.getUserType().contentEquals("USER")) {
				List<ClinicInfo> clinicInfo = userDao.findClinics();
				model.addAttribute("clinicInfo", clinicInfo);
				List<DailyNews> dailyNews = userDao.findDailyNews();
				model.addAttribute("todayNews", "NEWS FLASH: "+ dailyNews.get(0).getNews() +" - " + dailyNews.get(0).getPostedBy());
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
			} else {
				//Generate notifications
				String adminNotifications = "You have no notifications";
				List<User> userInfo = adminDao.findUnapprovedUsers();
				if(userInfo.size() > 0) {
					adminNotifications = "\nYou have "+userInfo.size()+ " pending user approval(s)";
				}
				List<Enquiry> userEnq = adminDao.findEnquiryByUser();
				int count = 0;
				for(int i=0; i< userEnq.size(); i++) {
					if(userEnq.get(i).getResponse().equalsIgnoreCase("Waiting Response!")) {
						count++;
					}
				}
				if(count > 0) {
					adminNotifications = "\nYou have "+count+ " pending enquiry(s)";
				}
				model.addAttribute("adminNotifications", adminNotifications);
				return "login-success-admin";
			}
		}
		return "login";
	}

}
