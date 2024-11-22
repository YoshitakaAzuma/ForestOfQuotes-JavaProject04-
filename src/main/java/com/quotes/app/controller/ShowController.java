package com.quotes.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quotes.app.entity.Quotes;
import com.quotes.app.helper.ShowHelper;
import com.quotes.app.service.GoodService;
import com.quotes.app.service.ProfileService;
import com.quotes.app.service.QuotesService;
import com.quotes.app.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/quotes")
@RequiredArgsConstructor
public class ShowController {
	
	private final QuotesService service;
	private final UserService userService;
	private final ProfileService profileService;
	private final GoodService goodService;
	
	// メニュー画面
	@GetMapping("/showmenu")
	public String showmenu(Model modle) {
		return "quotes/show/showmenu";
	}

	// 初期名言抽選
	@GetMapping("/all/getquotes")
	public String showQuotes(Model model) {
		Quotes quotes = service.randomQuotes();
		return "redirect:/quotes/all/show?id=" + quotes.getId();
	}
	
	// 連続二回目以降名言抽選
	// 前に閲覧した名言のidを省いて閲覧できる
	@GetMapping("/all/next")
	public String nextQuotes(@RequestParam("id") Integer id,Model model) {
		Quotes quotes = service.findQuotes(id);
		return "redirect:/quotes/all/show?id=" + quotes.getId();
	}
	
	@GetMapping("/all/show")
	public String showAll(@RequestParam("id") Integer id, Model model) {
		Quotes quotes = service.findQuotesById(id);
		model = ShowHelper.goodCheck(model, goodService.existsGood(userService.getLoginUserId(), quotes.getId()));
		model.addAttribute("quotes", quotes);
		model.addAttribute("favoriteId", profileService.findProfileByUserId(userService.getLoginUserId()).getFavorite_quotes_id());
		return "quotes/show/allquotes";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
