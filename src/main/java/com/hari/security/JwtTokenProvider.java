//package com.hari.security;
//
//import java.awt.RenderingHints.Key;
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoder;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Component 
//public class JwtTokenProvider {
//	
//	@Value("${auth.jwt-secret}")
//	private String jwtSecret;
//	@Value("${app.jwt-expiration-milliseconds}")
//	private Long jwtExpirationDate;
//	
//	public String generateToken(Authentication authentication) {
//		String username = authentication.getName();
//		
//		Date currentDate = new Date();
//		
//		Date expireDate=new Date(currentDate.getTime()+jwtExpirationDate);
//		
//		 String token = Jwts.builder()
//		 .setSubject(username)
//		 .setIssuedAt(new Date())
//		 .setExpiration(expireDate)
//		 .signWith(key())
//		 .compact();
//		 
//		
//		return token;
//	}
//	
//	private java.security.Key key() {
//		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
//	}
//	
//	public String getUsername(String token) {
//		
//	     Claims claims = Jwts.parser()   //Jwts.parser()
//	     .setSigningKey(key())
//	     .build()
//	     .parseClaimsJws(token)
//	     .getBody();
//	     
//	     String username = claims.getSubject();		
//		return username;
//	}
//	
//	public boolean validateToken(String token) {
//		
//		Jwts.parser()
//		.setSigningKey(key())
//		.build()
//		.parse(token);
//		
//		return true;
//	}
//	
//
//}
