package com.quotes.app.helper;

import com.quotes.app.entity.Profile;
import com.quotes.app.form.ProfileForm;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProfileHelper {
	
	public static Profile convertProfile(ProfileForm form){
		Profile profile = new Profile();
		profile.setUser_id(form.getUserId());
		profile.setFavorite_quotes_id(form.getFavoriteQuotesId());
		return profile;
		
	}
	
	public static String convertURL(String fileName) {
		System.out.println(fileName);
		return "/image/" + fileName;
	}
	
	
	
	
	
	

}
