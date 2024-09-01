package com.hari.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hari.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUserName(String userName);
	
	Boolean existsByEmail(String email);
	
	Optional<User> findByUserNameOrEmail(String userName,String email);

	Boolean existsByUserName(String username);
}
