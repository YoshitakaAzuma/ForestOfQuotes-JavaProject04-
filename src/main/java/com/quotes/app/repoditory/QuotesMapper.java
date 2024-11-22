package com.quotes.app.repoditory;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.quotes.app.entity.Quotes;

@Mapper
public interface QuotesMapper {
	
	List<Quotes> selectAllQuotesListByUserId(int user_id);
	
	List<Quotes> selectMineQuotesListByUserId(int user_id);
	
	
	int selectMaxId();
	
	Quotes selectById(@Param("id") int id);
	
	void insert(Quotes quotes);
	
	void updateGood(int id,int good);
	
	void deleteQuotesByQuotesId(int id);
}