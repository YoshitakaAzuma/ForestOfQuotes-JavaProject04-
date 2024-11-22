package com.quotes.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quotes.app.entity.Quotes;
import com.quotes.app.form.QuotesForm;
import com.quotes.app.helper.QuotesHelper;
import com.quotes.app.service.GoodService;
import com.quotes.app.service.GptService;
import com.quotes.app.service.ProfileService;
import com.quotes.app.service.QuotesService;
import com.quotes.app.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/quotes/create")
@RequiredArgsConstructor
public class CreateController {
	private final QuotesService service;
	private final UserService userService;
	private final GptService gptService;
	private final ProfileService profileService;
	private final GoodService goodService;
	
	
	// 名言作成画面
		@GetMapping
		public String createQuotes(Model model) {
			QuotesForm form = new QuotesForm();
			model.addAttribute("quotesForm", form);
			return "quotes/create";
		}
		
		@PostMapping("/gptsupport")
		public String gptsupport(@ModelAttribute("form")QuotesForm form, Model model) {
			
			System.out.println(form.getQuotes());
			
			System.out.println("ここ来てる");
			
			model.addAttribute("gptSupport",gptService.checkCategory(form.getQuotes()));
			model.addAttribute("quotesForm", form);
			return "quotes/create";
		}
		
		// 内容チェック
		@PostMapping("/checkcontents")
		public String check(QuotesForm form,Model model, RedirectAttributes attributes) {
			
			System.out.println(form.toString());

			// GPTによる内容チェック
			if(gptService.checkSentence(form.getQuotes()) || gptService.checkSentence(form.getWhose())) {
				model.addAttribute("form", form);
				return "quotes/warning";
			}
			// フォームの内容をリダイレクト属性に追加
		    attributes.addFlashAttribute("form", form);
		    return "redirect:/quotes/create/submitForm";
		}
		
		@GetMapping("/submitForm")
		public String submitForm(@ModelAttribute("form") QuotesForm form, Model model) {
		    if (form != null) {
		    	form = QuotesHelper.checkMine(form, userService.getLoginUsername());
		        model.addAttribute("form", form);
		        return "quotes/submit";
		    }
		    return "redirect:/error"; // エラー処理やフォームがない場合の処理
		}
		
		// 名言作成実行
		@PostMapping("/executioncreate")
		public String create(@Validated QuotesForm form,BindingResult result,RedirectAttributes attributes,Model model) {
			
			System.out.println(form.toString());
			
			if(result.hasErrors()) {
				model = QuotesHelper.error(result, model);
				return "quotes/create";
			}
			Quotes quotes = QuotesHelper.convertQuotes(form);
			quotes.setUser_id(userService.getLoginUserId());
			service.insertQuotes(quotes);
			attributes.addFlashAttribute("message", "新しい名言が追加されました！");
			return "redirect:/quotes/create";
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
