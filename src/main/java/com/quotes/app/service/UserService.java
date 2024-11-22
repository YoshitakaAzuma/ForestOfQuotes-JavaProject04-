package com.quotes.app.service;

import java.time.LocalDate;

import com.quotes.app.entity.UserEntity;

public interface UserService {
	
	String getLoginUsername();
	
	int getLoginUserId();
	
	UserEntity findUserEntityByUsername(String username);
	
	UserEntity findUserEntityByEmail(String email);
	
	UserEntity findUserEntityById(int id);
	
	void addUSER (UserEntity user);
	
	void updateLimitedPeriodById(int id);
	
	void updateBanById(int id);
	
	LocalDate getLimitedPeriodById(int id);
	
	
}
