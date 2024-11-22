package com.quotes.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.quotes.app.entity.Follow;
import com.quotes.app.entity.UserEntity;
import com.quotes.app.repoditory.FollowMapper;
import com.quotes.app.repoditory.UserMapper;
import com.quotes.app.service.FollowService;
import com.quotes.app.service.ProfileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{
	
	private final FollowMapper followMapper;
	private final UserMapper userMapper;
	private final ProfileService profileService;
	
	@Override
	public List<Integer> findFollowUserIdList(int user_id) {
		List<Follow> followList = followMapper.selectByUserId(user_id);
		List<Integer> list = new ArrayList<>();
		for (Follow f : followList) {
			list.add(f.getFollow_id());
		}
		return list;
		
		
	}
	
	@Override
	public List<UserEntity> findFollowUserEntity(int user_id) {
		List<Integer> list = findFollowUserIdList(user_id);
		List<UserEntity> userList = new ArrayList<>();
		for(int i :list) {
			userList.add(userMapper.getUserEntityById(i));
		}
		return userList;
	}

	@Override
	public List<Integer> findFollowerUserIdList(int user_id) {
		List<Follow> followList = followMapper.selectByFollowId(user_id);
		List<Integer> list = new ArrayList<>();
		for (Follow f : followList) {
			list.add(f.getUser_id());
		}
		return list;
	}

	
	@Override
	public List<UserEntity> findFollowerUserEntity(int user_id) {
		List<Integer> list = findFollowerUserIdList(user_id);
		List<UserEntity> userList = new ArrayList<>();
		for(int i :list) {
			userList.add(userMapper.getUserEntityById(i));
		}
		return userList;
	}
	@Override
	public void addFollow(int user_id, int follow_id) {
		Follow follow = new Follow();
		follow.setUser_id(user_id);
		follow.setFollow_id(follow_id);
		followMapper.insert(follow);
	}

	@Override
	public void deleteFollow(Follow follow) {
		followMapper.delete(follow);
	}

	@Override
	public boolean existsFollow(int userId, int followId) {
		Follow follow = followMapper.selectByUserIdAndFollowId(userId, followId);
		if (follow == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean changeFollowAndReaponse(int userId, int followId) {
		if (existsFollow(userId,followId)){
			deleteFollow(followMapper.selectByUserIdAndFollowId(userId, followId));
			
			System.out.println("Follow削除");
			
			profileService.updateFollowByUserId(userId);
			profileService.updateFollowerByuserId(followId);
			
			return false;
		}
		
		System.out.println("Follow作成");
		
		addFollow(userId,followId);
		profileService.updateFollowByUserId(userId);
		profileService.updateFollowerByuserId(followId);

		return true;
	}

}
