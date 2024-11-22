package com.quotes.app.comparator;

import java.util.Comparator;

import com.quotes.app.entity.Quotes;

public class QuotesComparator implements Comparator<Quotes>{

	@Override
	public int compare(Quotes q1, Quotes q2) {
		// TODO 自動生成されたメソッド・スタブ
		return q1.getId() - q2.getId();
	}

}
