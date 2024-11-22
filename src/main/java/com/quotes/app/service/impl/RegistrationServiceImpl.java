package com.quotes.app.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.quotes.app.entity.UserEntity;
import com.quotes.app.form.SignupForm;
import com.quotes.app.service.RegistrationService;
import com.quotes.app.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService{

	private final UserService userService;
    private final PasswordEncoder passwordEncoder;
	
	@Override
	public void registerUser(SignupForm form) throws Exception {
		// 二重登録のチェック
		if (userService.findUserEntityByUsername(form.getUsername()) != null) {
			System.out.println("名前重複");
			throw new Exception("ユーザ名が既に存在します。");
		}
		if (userService.findUserEntityByEmail(form.getEmail()) != null) {
			System.out.println("メールアドレス重複");
			throw new Exception("メールアドレスが既に存在します。");
		}
		// パスワード一致のチェック
		if (!form.getPassword().equals(form.getPasswordConfirm())) {
			System.out.println("パスワード不一致");
			throw new Exception("パスワードと確認用パスワードが一致しません。");
		}
        
		//新しい」ユーザエンティティの作成と保存
		UserEntity user = new UserEntity();
		user.setUsername(form.getUsername());
		user.setEmail(form.getEmail());
        // パスワードをハッシュ化してセットする
		user.setPassword_hash(passwordEncoder.encode(form.getPassword()));
        user.setBanned(false);
		
        userService.addUSER(user);
        
        
        
	}

}
