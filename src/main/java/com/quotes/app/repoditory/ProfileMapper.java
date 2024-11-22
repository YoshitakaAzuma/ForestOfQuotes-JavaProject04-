package com.quotes.app.repoditory;

import org.apache.ibatis.annotations.Mapper;

import com.quotes.app.entity.Profile;

@Mapper
public interface ProfileMapper {
	
	Profile getProfileByUserId(int userId);
	
	void insertProfile(Profile profile);
	
	void updateProfile(Profile profile);
	
}
