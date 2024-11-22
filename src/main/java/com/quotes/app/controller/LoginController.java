package com.quotes.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quotes.app.form.LoginForm;

@Controller
@RequestMapping
public class LoginController {
	
	@GetMapping("/login")
	public String login(@ModelAttribute LoginForm form) {
		return "login";
	}
	
	@GetMapping("/signup")
	public String regist(Model model) {
		return "signup";
	}

}
