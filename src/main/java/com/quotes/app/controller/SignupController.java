package com.quotes.app.controller;

import java.net.URI;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.quotes.app.entity.Profile;
import com.quotes.app.form.SignupForm;
import com.quotes.app.service.ProfileService;
import com.quotes.app.service.RegistrationService;
import com.quotes.app.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignupController {

		private final RegistrationService userRegistrationService;
		private final ProfileService profileService;
		private final UserService userService;
	private static final String BASE_FRONTEND_URL = "http://localhost:8080";
	
	
	
	// ユーザー登録のエンドポイント
	@PostMapping("/registration")
	public ModelAndView registration(@ModelAttribute SignupForm signupForm,RedirectAttributes attributes) {
	
		System.out.println("新規登録エンドポイント");
		
	    // バリデーションエラーのチェック
		/*	if (bindingResult.hasErrors()) {
			    String errorMessage = bindingResult.getAllErrors().stream()
			            .map(error -> error.getDefaultMessage())
			            .collect(Collectors.joining(", "));
			    return redirectToErrorPage(errorMessage);
			}*/
	
	    try {
	        // ユーザー登録処理
	    	
	    	System.err.println("登録処理開始");
	    	
	        userRegistrationService.registerUser(signupForm);
	        attributes.addFlashAttribute("success","登録されました");
	        // プロフィールの設定
	        Profile profile = new Profile();
	        profile.setUser_id(userService.findUserEntityByUsername(signupForm.getUsername()).getId());
	        
	        profileService.addProfile(profile);
	        
	        
	        return new ModelAndView("redirect:" + BASE_FRONTEND_URL + "/login");
	    } catch (DataIntegrityViolationException ex) { // データベースの一意制約違反のハンドリング
	        String errorMessage = "メールアドレスが既に存在します";
	        return redirectToErrorPage(errorMessage);
	    } catch (Exception ex) {
	        // 登録エラーのハンドリング
	        return redirectToErrorPage(ex.getMessage());
	    }
	}
	
	// エラーメッセージを持ったエラーページへのリダイレクト
	private ModelAndView redirectToErrorPage(String errorMessage) {
	    UriComponentsBuilder builder = UriComponentsBuilder
	            .fromHttpUrl(BASE_FRONTEND_URL + "/signup")
	            .queryParam("error", errorMessage);
	
	    URI errorPageUri = builder.build().encode().toUri();
	    return new ModelAndView("redirect:" + errorPageUri.toString());
	}

}
