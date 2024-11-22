package com.quotes.app.helper;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.quotes.app.entity.Quotes;
import com.quotes.app.form.QuotesForm;

public class QuotesHelper {
	
	public static Quotes convertQuotes(QuotesForm form) {
		Quotes quotes = new Quotes();
		quotes.setQuotes(form.getQuotes());
		quotes.setCategory(form.getCategory());
		quotes.setWhose(form.getWhose());
		quotes.setWhose_detail(form.getWhose_detail());
		quotes.setMine(form.isMine());
		return quotes;
	}
	
	public static QuotesForm convertQuotesFrom(Quotes quotes) {
		QuotesForm form = new QuotesForm();
		form.setQuotes(quotes.getQuotes());
		form.setCategory(quotes.getCategory());
		form.setWhose(quotes.getWhose());
		form.setWhose_detail(quotes.getWhose_detail());
		form.setMine(quotes.isMine());
		return form;
	}
	
	public static Model error(BindingResult result, Model model) {
		for (ObjectError error : result.getAllErrors()) {
			if(error.getDefaultMessage().equals("名言は1～100文字以内で入力してください")) {
				model.addAttribute("quoteserror", error.getDefaultMessage());
				System.out.println("エラーquotes");
			} else if(error.getDefaultMessage().equals("発言者を入力してください")) {
				model.addAttribute("whoseerror", error.getDefaultMessage());
				System.out.println("エラーwhose");
			} else if(error.getDefaultMessage().equals("カテゴリを選択してください")) {
				model.addAttribute("whose_detailerror", error.getDefaultMessage());
				System.out.println("エラーwhose_detail");
			}
        }
		System.out.println("エラー発生");
		return model;
	}
	
	public static QuotesForm checkMine(QuotesForm form,String username) {
		if(form.isMine()) {
			form.setWhose(username);
		}
		return form;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
