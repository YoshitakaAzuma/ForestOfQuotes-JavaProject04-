package com.quotes.app.entity;

import lombok.Data;

@Data
public class Profile {
	private int id;
	private int user_id;
	
	private int favorite_quotes_id;
	
	private String image_file_name;
	
	private int follow;
	
	private int follower;
}
