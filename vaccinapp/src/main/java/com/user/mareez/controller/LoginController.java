package com.user.mareez.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.user.mareez.dao.StudentDao;
import com.user.mareez.dao.UserDao;
import com.user.mareez.model.LoginInfo;
import com.user.mareez.model.Student;
import com.user.mareez.model.User;

@Controller
@SessionAttributes("student")
public class LoginController {
	
	@Autowired
	UserDao userDao;
	
	
	/**
	 * Create new signUpForm object for empty from
	 * 
	 * @return
	 */
	@ModelAttribute("loginInfo")
	public LoginInfo loginForm() {
		return new LoginInfo();
	}

	/**
	 * Method to show the initial HTML form
	 * 
	 * @return
	 */
	@GetMapping("/login")
	public String login(HttpSession session) {
	    User user = (User) session.getAttribute("user");
	    if(user != null) {
	    	return "login-success";
	    }
	    return "login";
	}

	@PostMapping("/login") 
	public String login(@ModelAttribute("loginInfo") LoginInfo loginInfo, Model model){
		User user = userDao.findByEmail(loginInfo.getEmail());
		model.addAttribute("message", "Login Fail");

		if(user != null && user.getPassword().equals(loginInfo.getPassword())) {
			model.addAttribute("user", user);
			model.addAttribute("message", "Welcome, " + user.getFirstName() +"!");
			return "login-success";
		}
		return "login";
	}
	
}
