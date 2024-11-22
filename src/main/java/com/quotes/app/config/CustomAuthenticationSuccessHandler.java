package com.quotes.app.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// ユーザーの権限に基づいてリダイレクト先を決定
        String redirectUrl = determineTargetUrl(authentication);
        response.sendRedirect(redirectUrl);
		
	}
	
	protected String determineTargetUrl(Authentication authentication) {
        // 権限に基づくロジックをここに実装
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return "/admin/dashboard";
        } else {
            return "/quotes";
        }
    }

}
