package com.quotes.app.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileForm {
	
	private int userId;
	
	private MultipartFile imageFile;
	
	private int favoriteQuotesId;
	
	
}
