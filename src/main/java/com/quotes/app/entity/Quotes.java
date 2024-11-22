package com.quotes.app.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quotes {
	
	private int id;
	
	private String quotes;
	
	private int category;
	
	private String whose;
	
	private String whose_detail;
	
	private int user_id;
	
	private boolean mine;
	
	private LocalDateTime created_at;
	
	private int good;
}
