package com.quotes.app.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User{
	private String email;
	
	public CustomUserDetails(String username, 
			String password,
			Collection<? extends GrantedAuthority> authorities,
			String email) {
		super(username, password, authorities);
		this.email = email;
	}
	
	//【追加部分】
			public String getEmail() {
				return email;
			}
	
}
