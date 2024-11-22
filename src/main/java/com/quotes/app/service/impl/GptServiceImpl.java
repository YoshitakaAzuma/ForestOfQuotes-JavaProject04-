package com.quotes.app.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quotes.app.service.GptService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GptServiceImpl implements GptService{
	// APIキーを環境変数または設定ファイルから取得します
    @Value("${openai.api.key}")
    private String apiKey;
    
    // OpenAIのAPIエンドポイントURL
    private final String API_URL = "https://api.openai.com/v1/chat/completions";
    private final RestTemplate restTemplate;

	@Override
	public boolean checkSentence(String s) {
		String message = "以降の文にモラルに反する単語、攻撃的な単語が含まれているか YES or NO のみで答えてください。→" + deleteLineBreak(s);
		String yn = "";
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + apiKey); // APIキーをヘッダーに設定
		headers.set("Content-Type", "application/json"); // リクエストのコンテンツタイプを設定
		
		// APIリクエストのボディを作成
        //String body = "{\"model\":\"gpt-3.5-turbo\",\"messages\":[{\"role\":\"user\",\"content\":\"" + message + "\"}]}";
        String body = String.format("{\"model\":\"gpt-3.5-turbo\",\"messages\":[{\"role\":\"user\",\"content\":\"%s\"}], \"temperature\": 0}", message);
        HttpEntity<String> request = new HttpEntity<>(body, headers);
		
     // APIエンドポイントにPOSTリクエストを送信
        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, request, String.class);
		
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            // APIレスポンスからメッセージコンテンツを抽出
            yn = root.path("choices").get(0).path("message").path("content").asText();
            
            System.out.println(yn);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (yn.equals("YES")) {
        	return true;
        } 
        return false;
	}

	@Override
	public String checkCategory(String s) {
		String message = "人生と生き方・成功と挑戦・愛と人間関係・知恵と学び・希望と勇気　以上５し種類の内次の名言はどれにカテゴライズされますか→" + deleteLineBreak(s);
		String a = "";
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + apiKey); // APIキーをヘッダーに設定
		headers.set("Content-Type", "application/json"); // リクエストのコンテンツタイプを設定
		
		// APIリクエストのボディを作成
        //String body = "{\"model\":\"gpt-3.5-turbo\",\"messages\":[{\"role\":\"user\",\"content\":\"" + message + "\"}]}";
        String body = String.format("{\"model\":\"gpt-3.5-turbo\",\"messages\":[{\"role\":\"user\",\"content\":\"%s\"}], \"temperature\": 0}", message);
        HttpEntity<String> request = new HttpEntity<>(body, headers);
		
     // APIエンドポイントにPOSTリクエストを送信
        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, request, String.class);
		
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            // APIレスポンスからメッセージコンテンツを抽出
            a = root.path("choices").get(0).path("message").path("content").asText();
            
            System.out.println(a);
            
            a = "AIがオススメするカテゴリは「" + a + "」です";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
	}

	@Override
	public String deleteLineBreak(String s) {
		if(s != null) {
			s = s.replaceAll("\\r\\n|\\r|\\n", "");
		}
		return s;
	}
	
	
	

}
