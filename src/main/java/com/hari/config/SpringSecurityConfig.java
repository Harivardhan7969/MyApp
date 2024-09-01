package com.hari.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//import com.hari.security.JwtAuthenticationEntryPoint;
//import com.hari.security.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
//	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//	
//	private JwtAuthenticationFilter jwtAuthenticationFilter;
//	
//	

	@Bean
	public static PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf().disable()
		.authorizeHttpRequests(authorize->{
		
			authorize.requestMatchers(HttpMethod.POST,"api/**").hasRole("ADMIN");
//			authorize.requestMatchers(HttpMethod.PUT,"api/**").hasRole("ADMIN");
//			authorize.requestMatchers(HttpMethod.DELETE,"api/**").hasRole("ADMIN");
//			authorize.requestMatchers(HttpMethod.PATCH,"api/**").hasAnyRole("ADMIN","USER");
         authorize.requestMatchers(HttpMethod.GET,"api/todos").hasAnyRole("ADMIN","USER");
//            
//			authorize.requestMatchers("api/auth/**").permitAll();
//		    authorize.requestMatchers("/**").permitAll();
//			authorize.requestMatchers("/**").permitAll();
			//authorize.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll();
			authorize.anyRequest().authenticated();
		}).httpBasic(Customizer.withDefaults());
		
//		httpSecurity.exceptionHandling(exception->
//		   exception.authenticationEntryPoint(jwtAuthenticationEntryPoint));
//		httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//		
		return httpSecurity.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		
		return authenticationConfiguration.getAuthenticationManager();
	}
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails hari= org.springframework.security.core.userdetails.User
//				.builder()
//				.username("harivardhan")
//				.password(passwordEncoder().encode("password"))
//				.roles("USER")				
//				.build();
//		
//				UserDetails admin= User.builder()
//				.username("admin")
//				.password(passwordEncoder().encode("admin"))
//				.roles("ADMIN")
//				.build();	
//		return new InMemoryUserDetailsManager(hari,admin);
//	}

}
