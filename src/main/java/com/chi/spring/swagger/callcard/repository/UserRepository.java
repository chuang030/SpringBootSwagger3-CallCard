package com.chi.spring.swagger.callcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chi.spring.swagger.callcard.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
