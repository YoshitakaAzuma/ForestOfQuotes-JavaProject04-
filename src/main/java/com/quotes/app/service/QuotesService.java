package com.quotes.app.service;

import java.util.List;

import com.quotes.app.entity.Quotes;

public interface QuotesService {
	// ナンバーから名言を検索
	Quotes findQuotesById(int id);
	//ランダムなIDの名言を検索
	Quotes randomQuotes();
	// 引数のIdを省いた名言を検索
	Quotes findQuotes(int no);
	
	void insertQuotes(Quotes quotes);
	
	List<Quotes> findAllQuotesListByUserId(int userId);
	
	List<Quotes> findMineQuotesListByUserId(int userId);
	
	void updateGoodById(int id);
	
	boolean deleteQuotesByQuotesId(int id);
	
}
