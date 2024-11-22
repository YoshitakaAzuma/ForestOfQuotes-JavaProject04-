package com.quotes.app.repoditory;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.quotes.app.entity.Good;

@Mapper
public interface GoodMapper {
	
	List<Good> selectByUserId(int user_id);
	
	List<Good> selectByQuotesId(int quotes_id);
	
	Good selectByUserIdAndQuotesId(int user_id,int quotes_id);
	
	int insertGood(Good good);
	
	void deleteGood(Good good);

}
