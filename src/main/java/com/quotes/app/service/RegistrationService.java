package com.quotes.app.service;

import com.quotes.app.form.SignupForm;

public interface RegistrationService {
	void registerUser(SignupForm form)throws Exception;
	
}
