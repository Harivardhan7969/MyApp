package com.hari.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hari.dto.JwtAuthResponse;
import com.hari.dto.LoginDto;
import com.hari.dto.RegisterDto;
import com.hari.service.AuthService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
		
		String response = authService.register(registerDto);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
		String token = authService.login(loginDto);
		
		//JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		//jwtAuthResponse.setAccessToken(token);
		//return new ResponseEntity<>(jwtAuthResponse,HttpStatus.OK);
		return new ResponseEntity<>(token,HttpStatus.OK);
	}
	
}
