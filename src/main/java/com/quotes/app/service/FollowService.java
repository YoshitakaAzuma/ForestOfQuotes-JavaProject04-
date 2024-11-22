package com.quotes.app.service;

import java.util.List;

import com.quotes.app.entity.Follow;
import com.quotes.app.entity.UserEntity;

public interface FollowService {
	List<Integer> findFollowUserIdList(int user_id);
	
	List<UserEntity> findFollowUserEntity(int user_id);
	
	List<Integer> findFollowerUserIdList(int user_id);
	
	List<UserEntity> findFollowerUserEntity(int user_id);
	
	void addFollow(int user_id, int follow_id);
	
	void deleteFollow(Follow follow);
	
	boolean existsFollow(int userId, int followId);
	
	boolean changeFollowAndReaponse(int userId, int followId);

}
