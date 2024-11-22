package com.quotes.app.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quotes.app.entity.Quotes;
import com.quotes.app.repoditory.GoodMapper;
import com.quotes.app.repoditory.QuotesMapper;
import com.quotes.app.service.QuotesService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class QuotesServiceImpl implements QuotesService {
	Random rand = new Random();
	
	public final QuotesMapper mapper;
	public final GoodMapper goodMapper;
	
	@Override
	public Quotes findQuotesById(int id) {
		return mapper.selectById(id);
	}
	
	@Override
	public Quotes randomQuotes() {
		int no = rand.nextInt(1,mapper.selectMaxId() + 1);
		return mapper.selectById(no);
	}

	@Override
	public void insertQuotes(Quotes quotes) {
		mapper.insert(quotes);
		
	}

	@Override
	public Quotes findQuotes(int ex) {
		Random rand = new Random();
		int no = 0;
		do {
			no = rand.nextInt(1,mapper.selectMaxId() + 1);

		}while(no == ex);
		return mapper.selectById(no);
	}
	
	// 全ての名言をUserIdから取得
	@Override
	public List<Quotes> findAllQuotesListByUserId(int userId) {
		return mapper.selectAllQuotesListByUserId(userId);
	}
	
	// 自作名言をListで取得
	@Override
	public List<Quotes> findMineQuotesListByUserId(int userId) {
		return mapper.selectMineQuotesListByUserId(userId);
	}
	
	// いいね数の更新
	@Override
	public void updateGoodById(int id) {
		mapper.updateGood(id, goodMapper.selectByQuotesId(id).size());
	}
	
	// 名言の削除
	@Override
	public boolean deleteQuotesByQuotesId(int id) {
		mapper.deleteQuotesByQuotesId(id);
		if(findQuotesById(id) != null) { // 削除失敗
			return false;
		}
		return true;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
