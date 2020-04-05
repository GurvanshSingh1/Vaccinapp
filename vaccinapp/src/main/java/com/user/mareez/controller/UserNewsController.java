package com.user.mareez.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.user.mareez.dao.UserDao;
import com.user.mareez.model.DailyNews;
import com.user.mareez.model.User;

@SessionAttributes("user")
@Controller
public class UserNewsController {

	@Autowired
	UserDao userDao;
	
	@GetMapping("/viewDailyNews")
	public String viewDailyNews(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			List<DailyNews> dailyNews = userDao.findDailyNews();
			model.addAttribute("dailyNews", dailyNews);
		}
		return "view-all-news";
	}
			
}
