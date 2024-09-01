package com.hari.service.imp;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hari.dto.LoginDto;
import com.hari.dto.RegisterDto;
import com.hari.entity.Role;
import com.hari.entity.User;
import com.hari.exception.TodoAPIException;
import com.hari.repo.RoleRepository;
import com.hari.repo.UserRepository;
//import com.hari.security.JwtTokenProvider;
import com.hari.service.AuthService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{

	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	private AuthenticationManager authenticationManager;
	
	//private JwtTokenProvider jwtTokenProvider;
	
	@Override
	public String register(RegisterDto registerDto) {
		
		if(userRepository.existsByUserName(registerDto.getUsername())) {
			throw new TodoAPIException(HttpStatus.BAD_REQUEST, "User Name already exists");
		}
		if(userRepository.existsByEmail(registerDto.getEmail())) {
			throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Email already exists");
		}
		
		User user = new User();
		
		user.setName(registerDto.getName());
		user.setUserName(registerDto.getUsername());
		user.setEmail( registerDto.getEmail());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("USER_ROLE");
		
		roles.add(userRole);
		
		user.setRoles(roles);
		
		userRepository.save(user);
		
		
		return "User registered Successfully";
	}

	@Override
	public String login(LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(
				  new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword()) );
		
		  SecurityContextHolder.getContext().setAuthentication(authentication);
		  
		 // String token = jwtTokenProvider.generateToken(authentication);
		  
		 return "User Log in successfully";
		//  return token;
	}

	

}
