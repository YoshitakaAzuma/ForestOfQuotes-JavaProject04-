package com.quotes.app.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quotes.app.helper.ProfileHelper;
import com.quotes.app.helper.ShowHelper;
import com.quotes.app.service.FollowService;
import com.quotes.app.service.GoodService;
import com.quotes.app.service.ProfileService;
import com.quotes.app.service.QuotesService;
import com.quotes.app.service.UserService;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/quotes")
@RequiredArgsConstructor
public class QuotesController {
	
	private final QuotesService service;
	private final UserService userService;
	private final ProfileService profileService;
	private final GoodService goodService;
	private final FollowService followService;
	// タイトル画面
	@GetMapping
	public String menu(Model model) {
		userService.getLoginUsername();
		model.addAttribute("image", ProfileHelper.convertURL(profileService.findImageFileNameByUserId(userService.getLoginUserId())));
		return "quotes/home";
	}
	
	// お気に入り登録
	@GetMapping("/regist-favorite")
	public String regidtFavorite(@RequestParam("id")int id,Model model) {
		profileService.updateFavoriteQuotesId(id, userService.getLoginUserId());
		model = ShowHelper.goodCheck(model, goodService.existsGood(userService.getLoginUserId(), id));
		model.addAttribute("quotes", service.findQuotesById(id));
		model.addAttribute("favoriteId", profileService.findProfileByUserId(userService.getLoginUserId()).getFavorite_quotes_id());
		return "quotes/show/allquotes";
	}
	
	// お気に入り登録解除
	@GetMapping("/cancell-favorite")
	public String cancellFavorite(@RequestParam("id")int id,Model model) {
		profileService.deleteFavoriteQuotesId(userService.getLoginUserId());
		model = ShowHelper.goodCheck(model, goodService.existsGood(userService.getLoginUserId(), id));
		model.addAttribute("quotes", service.findQuotesById(id));
		model.addAttribute("favoriteId", profileService.findProfileByUserId(userService.getLoginUserId()).getFavorite_quotes_id());
		return "quotes/show/allquotes";
	}
	
	
	// いいね状態変更
	@GetMapping("/api/toggle-like")
	public @ResponseBody Map<String, Boolean> toggleLike(@RequestParam("quotes_id") Integer quotesId) {
		
		boolean liked = goodService.changeGoodAndResponse(userService.getLoginUserId(), quotesId); // 実際のいいね状態の変更ロジック
		return Collections.singletonMap("liked", liked);
	}
	
	// お気に入り状態変更
	@GetMapping("/api/toggle-favorite")
	public @ResponseBody Map<String, Boolean> toggleFavorite(@RequestParam("quotes_id") Integer quotesId){
		
		boolean favorited = profileService.changeFavoriteQuotesAndResponse(userService.getLoginUserId(), quotesId);
		return Collections.singletonMap("favorited", favorited);
	}

	// フォロー状態変更
	@GetMapping("/api/toggle-follow")
	public @ResponseBody Map<String, Boolean> toggleLikeFollow(@RequestParam("follow_id") Integer followId){
		
		boolean followed = followService.changeFollowAndReaponse(userService.getLoginUserId(), followId);
		return Collections.singletonMap("followed", followed);
	}
	
	@GetMapping("/api/delete")
    @ResponseBody
    public ResponseEntity<?> deleteQuote(@RequestParam("quotes_id") int quotes_id) {
        try {
            boolean isDeleted = service.deleteQuotesByQuotesId(quotes_id);
            if (isDeleted) {
                return ResponseEntity.ok().body(Map.of("deleted", true));
            } else {
                return ResponseEntity.badRequest().body(Map.of("deleted", false, "message", "削除に失敗しました。"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("deleted", false, "message", "エラーが発生しました。"));
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
