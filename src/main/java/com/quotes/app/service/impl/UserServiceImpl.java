package com.quotes.app.service.impl;

import java.time.LocalDate;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quotes.app.entity.UserEntity;
import com.quotes.app.repoditory.UserMapper;
import com.quotes.app.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserMapper userMapper;
	
	
	@Override // username取得
	public String getLoginUsername() {
		final String name = SecurityContextHolder.getContext().getAuthentication().getName();
		if(name == null) {
			System.out.println("username取得失敗");
			return "Noname";
		}
		// System.out.println(name);
		return name;
	}
	
	@Override // userId取得
	public int getLoginUserId() {
		int userId = userMapper.getUserEntityByUsername(getLoginUsername()).getId();
		return userId;
		
		
	}

	@Override
	public UserEntity findUserEntityByUsername(String username) {
		return userMapper.getUserEntityByUsername(username);
	}
	
	@Override
	public UserEntity findUserEntityByEmail(String email) {
		return userMapper.getUserEntityByEmail(email);
	}
	
	@Override
	public UserEntity findUserEntityById(int id) {
		return userMapper.getUserEntityById(id);
	}

	@Override
	public void addUSER(UserEntity user) {
		userMapper.insertUSER(user);
	}

	@Override
	public void updateLimitedPeriodById(int id) {
		userMapper.updateLimitedPeriodById(id);
	}

	@Override
	public void updateBanById(int id) {
		userMapper.updateBanById(id);
	}

	@Override
	public LocalDate getLimitedPeriodById(int id) {
		return userMapper.getLimitedPeriodById(id);
	}


}
