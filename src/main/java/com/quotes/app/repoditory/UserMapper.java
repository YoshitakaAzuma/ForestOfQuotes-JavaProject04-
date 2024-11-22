package com.quotes.app.repoditory;

import java.time.LocalDate;

import org.apache.ibatis.annotations.Mapper;

import com.quotes.app.entity.UserEntity;

@Mapper
public interface UserMapper {
	
	UserEntity getUserEntityByUsername(String username);
	
	UserEntity getUserEntityByEmail(String email);
	
	UserEntity getUserEntityById(int id);
	
	void insertUSER(UserEntity user);
	
	
	void updateLimitedPeriodById(int id);
	
	void updateBanById(int id);
	
	LocalDate getLimitedPeriodById(int id);

}