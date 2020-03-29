package com.user.mareez.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.mareez.model.User;

@Controller
public class IndexController {

	
	@RequestMapping("/index")
	public String handler(HttpSession session, Model model) {
	    User user = (User) session.getAttribute("user");
	    if(user != null) {
	    	session.invalidate();
	    }
		return "index";
	}
}
