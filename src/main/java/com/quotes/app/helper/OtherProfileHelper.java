package com.quotes.app.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.quotes.app.comparator.QuotesComparator;
import com.quotes.app.dto.QuotesDto;
import com.quotes.app.entity.Quotes;

public class OtherProfileHelper {
	
	public static List<QuotesDto> convertQuotesDot(List<Quotes> quotesList,List<Integer> goodList, List<Integer> followList){
		List<QuotesDto> list = new ArrayList<>();
		// Quotesを並び替え
		Collections.sort(quotesList, new QuotesComparator());
		// データセット
		for (Quotes q : quotesList) {
			QuotesDto dto = new QuotesDto();
			dto.setId(q.getId());
			dto.setQuotes(q.getQuotes());
			dto.setCategory(q.getCategory());
			dto.setWhose(q.getWhose());
			dto.setWhose_detail(q.getWhose_detail());
			dto.setUser_id(q.getUser_id());
			dto.setMine(q.isMine());
			dto.setGood(q.getGood());
			dto.setCreated_at(q.getCreated_at());
			dto.setExistsGood(goodList.contains(q.getId()));
			dto.setFollowing(followList.contains(q.getUser_id()));
			list.add(dto);
		}		
		return list;
	}
	
}
