package com.quotes.app.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.quotes.app.entity.Profile;
import com.quotes.app.repoditory.FollowMapper;
import com.quotes.app.repoditory.ProfileMapper;
import com.quotes.app.service.ProfileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService{
	
	private final ProfileMapper profileMapper;
	private final FollowMapper followMapper;
	@Value("${image.folder}")
	private String imgFolder;

	@Override
	public Profile findProfileByUserId(int userId) {
		return profileMapper.getProfileByUserId(userId);
	}
	
	// フォロー取得
	@Override
	public int findFollowByUserId(int userId) {
		Profile profile = findProfileByUserId(userId);
		return profile.getFollow();
	}
	
	// フォロワー取得
	@Override
	public int findFollowerByUserId(int userId) {
		Profile profile = findProfileByUserId(userId);
		return profile.getFollower();
	}
	
	// ユーザーIDからプロフィール画像名を取得
	@Override
	public String findImageFileNameByUserId(int userId) {
		return findProfileByUserId(userId).getImage_file_name();
	}
	
	// プロフィールの追加
	@Override
	public void addProfile(Profile profile) {
		// staticフォルダからサンプル画像のバイトコードを生成
		Resource resource =  new ClassPathResource("static/image/sample.jpg");

		System.out.println("userid:" + profile.getUser_id());

		// 保存する画像ファイルのパス設定
		String saveFileName = profile.getUser_id() + "" +  System.currentTimeMillis() + ".jpg";
		Path imgFilePath = Path.of(imgFolder, saveFileName);
		
		File file = imgFilePath.toFile();
		if (file.exists()) {
			file.delete();
		}
		
		System.out.println("ここ来てる");

		// 画像ファイルの保存(フォルダ)
		try {
			
			System.out.println("保存");
			
			Files.copy(resource.getInputStream(), imgFilePath);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		// DB更新
		profile.setImage_file_name(saveFileName);
		profileMapper.insertProfile(profile);
	}
	
	// プロフィールのアップデート
	@Override
	public void updataProfile(Profile profile) {
		profileMapper.updateProfile(profile);
	}
	
	// お気に入り名言の更新
	@Override
	public void updateFavoriteQuotesId(int id, int userId) {
		Profile profile = findProfileByUserId(userId);
		profile.setFavorite_quotes_id(id);
		updataProfile(profile);
	}
	
	// お気に入り名言の削除
	@Override
	public void deleteFavoriteQuotesId(int userId) {
		Profile profile = findProfileByUserId(userId);
		profile.setFavorite_quotes_id(0);
		updataProfile(profile);
	}
	
	
	// フォローの更新
	@Override
	public void updateFollowByUserId(int userId) {
		Profile profile = profileMapper.getProfileByUserId(userId);
		profile.setFollow(followMapper.selectByUserId(userId).size());
		profileMapper.updateProfile(profile);
	}
	
	// フォロワーの更新
	@Override
	public void updateFollowerByuserId(int userId) {
		Profile profile = profileMapper.getProfileByUserId(userId);
		profile.setFollower(followMapper.selectByFollowId(userId).size());
		profileMapper.updateProfile(profile);
	}
	
	// プロフィール画像名の更新
	@Override
	public void updateImageFileNameByUserId(int userId, String imageFileName) {
		Profile profile = profileMapper.getProfileByUserId(userId);
		profile.setImage_file_name(imageFileName);
		profileMapper.updateProfile(profile);
	}
	
	
	@Override
	public boolean isItFavoriteQuotes(int userId, int quotesId) {
		Profile profile = findProfileByUserId(userId);
		if(profile.getFavorite_quotes_id() == quotesId) {
			return true;
		}
		return false;
	}

	@Override
	public boolean changeFavoriteQuotesAndResponse(int userId, int quotesId) {
		if(isItFavoriteQuotes(userId,quotesId)) {
			deleteFavoriteQuotesId(userId);
			
			System.out.println("お気に入り解除");
			
			return false;
		}
		
		System.out.println("お気に入り登録");
		
		updateFavoriteQuotesId(quotesId, userId);
		return true;
	}
	


	
}
