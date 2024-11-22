package com.quotes.app.helper;

import org.springframework.ui.Model;

public class ShowHelper {
	
	public static Model goodCheck(Model model,boolean b) {
		if (b) {
			model.addAttribute("existsGood", true);
			return model;
		}
		return model;
	}
	

}
