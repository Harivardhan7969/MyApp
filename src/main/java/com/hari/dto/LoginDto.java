package com.hari.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDto {
	
	private String usernameOrEmail;
	private String password;
	

}
