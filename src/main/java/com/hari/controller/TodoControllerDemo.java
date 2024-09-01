package com.hari.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


public class TodoControllerDemo{
	
	public static void main(String[] args) {
		PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
		System.out.println("password "+passwordEncoder.encode("password"));
		System.out.println("admin "+passwordEncoder.encode("admin"));
	}

}
