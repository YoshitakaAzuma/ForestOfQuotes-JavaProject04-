package com.quotes.app.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.quotes.app.entity.Profile;
import com.quotes.app.entity.UserEntity;
import com.quotes.app.helper.OtherProfileHelper;
import com.quotes.app.helper.ProfileHelper;
import com.quotes.app.service.FollowService;
import com.quotes.app.service.GoodService;
import com.quotes.app.service.ProfileService;
import com.quotes.app.service.QuotesService;
import com.quotes.app.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/quotes/profile")
@RequiredArgsConstructor
public class ProfileController {
	private final ProfileService profileService;
	private final UserService userService;
	private final QuotesService quotesService;
	private final GoodService goodService;
	private final FollowService followService;
	
	@Value("${image.folder}")
	private String imgFolder;
	
	
	
	// プロフィール画面へ 　①
	@GetMapping
	public String profile(@RequestParam(value = "userId", required = false, defaultValue = "0")int userId, Model model) {
		
		if(userId == userService.getLoginUserId()) {
			model.addAttribute("mine", true);
		}
		if (userId == 0) {  // RequestParamが空だった場合
			model.addAttribute("myprofile", true);
			userId = userService.getLoginUserId();
		}
		model.addAttribute("image", ProfileHelper.convertURL(profileService.findImageFileNameByUserId(userId)));
		model.addAttribute("profile", profileService.findProfileByUserId(userId));
		model.addAttribute("username", userService.findUserEntityById(userId).getUsername());
		model.addAttribute("favoriteQuotes", quotesService.findQuotesById(profileService.findProfileByUserId(userId).getFavorite_quotes_id()));
		model.addAttribute("isFollowing", followService.existsFollow(userService.getLoginUserId(), userId));
		model.addAttribute("follow", profileService.findFollowByUserId(userId));
		model.addAttribute("follower", profileService.findFollowerByUserId(userId));
		model.addAttribute("madeQuotesList", OtherProfileHelper.convertQuotesDot(quotesService.findAllQuotesListByUserId(userId), goodService.findoGoodQuotesIdListByUserId(userService.getLoginUserId()), followService.findFollowUserIdList(userService.getLoginUserId()))) ;
		model.addAttribute("followIdList", followService.findFollowUserIdList(userId));
		return "quotes/profile/profile2";
	}
	
	// プロフィール画像編集
	@GetMapping("/edit")
	public String edit(Model model) {
		//model.addAttribute("image", ProfileHelper.getByteByUserId(profileService.findProfileByUserId(userService.getLoginUserId())));
		model.addAttribute("profile", profileService.findProfileByUserId(userService.getLoginUserId()));
		model.addAttribute("favoriteQuotes", quotesService.findQuotesById(profileService.findProfileByUserId(userService.getLoginUserId()).getFavorite_quotes_id()));
		return "quotes/profile/editprofile";
	}
	
	// プロフィール画像保存
	@PostMapping("/save-image")
	public String save(@RequestParam MultipartFile image) {
		int userId = userService.getLoginUserId();
		Profile profile = profileService.findProfileByUserId(userService.getLoginUserId());
		
		System.out.println("プロフィール画像保存");
		
		
		
		if (!image.isEmpty()) {
			// 保存する画像ファイルのパス設定
			String saveFileName = userId + "" +  System.currentTimeMillis() + ".jpg";
			Path imgFilePath = Path.of(imgFolder, saveFileName);
			// 既存ファイルの削除
			if (profileService.findImageFileNameByUserId(userId) != null) {
				Path FilePath = Path.of(imgFolder, profileService.findImageFileNameByUserId(userId));
				File file = FilePath.toFile();
				if (file.exists()) {
					file.delete();
				}	
			}
			// 画像ファイルの保存(フォルダ)
			try {
				Files.copy(image.getInputStream(), imgFilePath);
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			// データベースへの保存
			profileService.updateImageFileNameByUserId(userId, saveFileName);
		}
		
		try {
			Thread.sleep(3300);
		}catch (InterruptedException e) {
			
		}

		// プロフィール画面へのリダイレクト
		String redirectUrl = "/quotes/profile?ts=" + System.currentTimeMillis();
		return "redirect:" + redirectUrl;
	}

	// プロフィール画面でのお気に入り解除
	@GetMapping("/cancell-favorite")
	public String cancellFavorite() {
		profileService.updateFavoriteQuotesId(0, userService.getLoginUserId());
		return "redirect:/quotes/profile";
	}	
	
	// 作成した名言一覧　②
	@GetMapping("/quoteslist")
	public String quotes(@RequestParam(value = "userId", required = false, defaultValue = "0")int userId, Model model) {
		if(userId == 0) {
			model.addAttribute("mine", true);
			userId = userService.getLoginUserId();
		}
		model.addAttribute("quotes", OtherProfileHelper.convertQuotesDot(quotesService.findAllQuotesListByUserId(userId), goodService.findoGoodQuotesIdListByUserId(userService.getLoginUserId()), followService.findFollowUserIdList(userService.getLoginUserId())));
		if(userId == userService.getLoginUserId()) {
			model.addAttribute("username", "あなた");
			return "quotes/profile/quoteslist";
		}
		model.addAttribute("username", userService.findUserEntityById(userId).getUsername());
		return "quotes/profile/quoteslist";
	}

	// いいねした名言一覧
	@GetMapping("/goodlist")
	public String goodlist(Model model) {
		model.addAttribute("goodList", goodService.findQuotesListByUserId(userService.getLoginUserId()));
		return "quotes/profile/goodlist";
	}
	

	// いいねしたユーザリスト
	@GetMapping("/gooduser")
	public String gooduser(@RequestParam("quotesId")int quotesId, Model model) {
		for (UserEntity user :goodService.findGoodUserEntityListByQuotesId(quotesId)) {
			System.out.println(user.toString());
		}
		
		model.addAttribute("userList", goodService.findGoodUserEntityListByQuotesId(quotesId));
		return "quotes/profile/gooduser";
	}
	
	// フォロー一覧
	@GetMapping("/followlist")
	public String followlist(@RequestParam("userId")int userId, Model model) {
		model.addAttribute("userList", followService.findFollowUserEntity(userId));
		return "quotes/profile/followlist";
	}
	
	// フォロワー一覧
	@GetMapping("/followerlist")
	public String followerlist(@RequestParam("userId") int userId, Model model) {
		model.addAttribute("userList", followService.findFollowerUserEntity(userId));
		return "quotes/profile/followerlist";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
