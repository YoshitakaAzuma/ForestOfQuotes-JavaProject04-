package com.quotes.app.service;

import com.quotes.app.entity.Profile;

public interface ProfileService {
	
	Profile findProfileByUserId(int userId);
	
	int findFollowByUserId(int userId);
	
	int findFollowerByUserId(int userId);
	
	String findImageFileNameByUserId(int userId);
	
	void addProfile(Profile profile);
	
	void updataProfile(Profile profile);
	
	void updateFavoriteQuotesId(int id,int userId);
	
	void deleteFavoriteQuotesId( int userId);
	
	void updateFollowByUserId(int userId);
	
	void updateFollowerByuserId(int userId);
	
	void updateImageFileNameByUserId(int userId, String imageFileName);
	
	boolean isItFavoriteQuotes(int userId, int quotesId);
	
	boolean changeFavoriteQuotesAndResponse(int userId, int quotesId);
	
}
