package com.quotes.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http
		// ★HTTPリクエストに対するセキュリティ設定
		.authorizeHttpRequests(authz -> authz
		// 「/login」へのアクセスは認証を必要としない
		.requestMatchers("/login", "/signup","/registration","/quotes").permitAll()
		// 【管理者権限設定】url:/todos/**は管理者しかアクセスできない
		.requestMatchers("/admin/**").hasAuthority("ADMIN")
		// その他のリクエストは認証が必要
		.anyRequest().authenticated())
		// ★フォームベースのログイン設定
		.formLogin(form -> form
		// カスタムログインページのURLを指定
		.loginPage("/login")
		// ログイン処理のURLを指定
		.loginProcessingUrl("/authentication")
		// ユーザー名のname属性を指定
		.usernameParameter("username")
		// パスワードのname属性を指定
		.passwordParameter("password")
		// ログイン成功時のリダイレクト先を指定
		.successHandler(customAuthenticationSuccessHandler)  // カスタム成功ハンドラを使用
		// ログイン失敗時のリダイレクト先を指定
		.failureUrl("/login?error"))
		// ★ログアウト設定
		.logout(logout -> logout
		// ログアウトを処理するURLを指定
		.logoutUrl("/logout")
		// ログアウト成功時のリダイレクト先を指定
		.logoutSuccessUrl("/login?logout")
		// ログアウト時にセッションを無効にする
		.invalidateHttpSession(true)
		// ログアウト時にCookieを削除する
		.deleteCookies("JSESSIONID")
		);
		return http.build();
	}
}