package com.quotes.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.quotes.app.entity.Good;
import com.quotes.app.entity.Quotes;
import com.quotes.app.entity.UserEntity;
import com.quotes.app.repoditory.GoodMapper;
import com.quotes.app.service.GoodService;
import com.quotes.app.service.QuotesService;
import com.quotes.app.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoodServiceImple implements GoodService{
	
	private final GoodMapper goodMapper;
	private final QuotesService quotesService;
	private final UserService userService;
	
	// UesrIdからQuotesListを探す
	@Override
	public List<Quotes> findQuotesListByUserId(int userId) {
		List<Good> goodList = goodMapper.selectByUserId(userId);
		List<Quotes> list = new ArrayList<>();
		for (Good good : goodList) {
			list.add(quotesService.findQuotesById(good.getQuotes_id()));
		}
		return list;
	}
	
	// GoodListをQuotesIdから探す
	@Override
	public List<Good> findGoodListByQuotesId(int quotesId) {
		return goodMapper.selectByQuotesId(quotesId);
	}
	
	// UserIdからいいねしたQuotesIdListを取得
	@Override
	public List<Integer> findoGoodQuotesIdListByUserId(int userId) {
		List<Good> goodList = goodMapper.selectByUserId(userId);
		List<Integer> list = new ArrayList<>();
		for (Good good: goodList) {
			list.add(good.getQuotes_id());
		}
		return list;
	}
	
	// QuotesIdからいいねしたユーザのUserIdを取得
	@Override
	public List<UserEntity> findGoodUserEntityListByQuotesId(int quotesId) {
		List<Good> goodList = goodMapper.selectByQuotesId(quotesId);
		List<UserEntity> list = new ArrayList<>();
		for (Good good: goodList) {
			list.add(userService.findUserEntityById(good.getUser_id()));
		}
		return list;
	}
	
	
	// Good追加
	@Override
	public void addGood(int userId, int quotesId) {
		Good good = new Good();
		good.setUser_id(userId);
		good.setQuotes_id(quotesId);
		goodMapper.insertGood(good);
	}
	
	// Good削除
	@Override
	public void deleteGood(Good good) {
		goodMapper.deleteGood(good);
	}
	
	// 特定のGoodが存在するか
	@Override 
	public boolean existsGood(int userId, int quotesId) {
		Good good = goodMapper.selectByUserIdAndQuotesId(userId, quotesId);
		if(good == null) {
			return false;
		}
		return true;
	}
	
	// Good変更とレスポンス
	@Override 
	public boolean changeGoodAndResponse(int userId, int quotesId) {
		if(existsGood(userId,quotesId)) {
			deleteGood(goodMapper.selectByUserIdAndQuotesId(userId, quotesId));
			
			System.out.println("Good削除");
			
			quotesService.updateGoodById(quotesId);
			
			return false;
		}
		
		System.out.println("Good作成");
		
		addGood(userId,quotesId);
		quotesService.updateGoodById(quotesId);
		return true;
	}

	
	
	
	

}
