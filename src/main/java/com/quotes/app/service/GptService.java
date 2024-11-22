package com.quotes.app.service;

public interface GptService {
	
	boolean checkSentence(String s);
	
	String checkCategory(String s);
	
	String deleteLineBreak(String s);
	
}
