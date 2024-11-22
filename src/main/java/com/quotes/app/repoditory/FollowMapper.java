package com.quotes.app.repoditory;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.quotes.app.entity.Follow;

@Mapper
public interface FollowMapper {
	
	List<Follow> selectByUserId(int user_id);
	
	List<Follow> selectByFollowId(int follow_id);
	
	Follow selectByUserIdAndFollowId(int user_id, int follow_id);
	
	void insert(Follow follow);
	
	void delete(Follow follow);

}
