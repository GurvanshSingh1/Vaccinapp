package com.user.mareez.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
public class AdminNewsController {

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
	
	
	@PostMapping("/postNews")
	public String postNews(HttpSession session,
			@ModelAttribute("DailyNews") DailyNews dailyNews, Model model) {
		User user = (User) session.getAttribute("user");
		
		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());

		int result = adminDao.insertDailyNews(dailyNews.getNews(), user.getFirstName(), date);

		if (result > 0) {
			model.addAttribute("approvedNews", "News Posted!");
		}

		model.addAttribute("user", user);
		model.addAttribute("userName", user.getFirstName());
		model.addAttribute("message", "Welcome, " + user.getFirstName() + "!");
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
		if((count > 0) && !(userInfo.size() > 0))  {
			adminNotifications = "\nYou have "+count+ " pending enquiry(s)";
		}
		
		if((count > 0) && (userInfo.size() >0))  {
			adminNotifications += "<br>You have "+count+ " pending enquiry(s)";
		}
		
		
		model.addAttribute("adminNotifications", adminNotifications);
		return "login-success-admin";
	}
	
}
