package com.quotes.app.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuotesDto {

	private int id;
	
	private String quotes;
	
	private int category;
	
	private String whose;
	
	private String whose_detail;
	
	private int user_id;
	
	private boolean mine;
	
	private int good;
	
	private LocalDateTime created_at;
	
	private boolean existsGood;
	
	private boolean isFollowing;
}
