package com.quotes.app.service;

import java.util.List;

import com.quotes.app.entity.Good;
import com.quotes.app.entity.Quotes;
import com.quotes.app.entity.UserEntity;

public interface GoodService {
	
	List<Quotes> findQuotesListByUserId(int userId);
	
	List<Good> findGoodListByQuotesId(int quotesId);
	
	List<Integer> findoGoodQuotesIdListByUserId(int userId);
	
	List<UserEntity> findGoodUserEntityListByQuotesId(int quotesId);
	
	void addGood(int userId, int quotesId);
	
	void deleteGood(Good good);
	
	boolean existsGood(int userId,int quotesId);
	
	boolean changeGoodAndResponse(int userId, int quotesId);
	
}
