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

import com.user.mareez.dao.UserDao;
import com.user.mareez.model.ClinicInfo;
import com.user.mareez.model.User;

@SessionAttributes("user")
@Controller
public class UserClinicsController {

	@Autowired
	UserDao userDao;
	
	@ModelAttribute("ClinicInfo")
	public ClinicInfo clinicInfo() {
		return new ClinicInfo();
	}
		
	@GetMapping("/viewClinicDetails")
	public String viewClinicDetails(@RequestParam("clinicId") String clinicId, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			List<ClinicInfo> clinicInfo = userDao.findClinics(Integer.parseInt(clinicId));
			model.addAttribute("clinicInfo", clinicInfo);
		}
		return "view-all-clinics";
	}
}
