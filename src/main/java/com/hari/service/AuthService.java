package com.hari.service;


import com.hari.dto.LoginDto;
import com.hari.dto.RegisterDto;

import lombok.AllArgsConstructor;


public interface AuthService {
	
	public String register(RegisterDto registerDto);
	
	public String login(LoginDto loginDto);

}
