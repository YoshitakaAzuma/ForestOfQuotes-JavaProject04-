package com.quotes.app.entity;

import lombok.Data;

@Data
public class UserEntity {
	private int id;
	private String username;
	private String password_hash;
	private String email;
	private Role authority;
	private boolean banned;
}
