package com.chi.spring.swagger.callcard.service;

import java.util.List;

import com.chi.spring.swagger.callcard.model.UserDTO;
import com.chi.spring.swagger.callcard.model.UserReviewDTO;
import com.chi.spring.swagger.callcard.model.UserUpdateDTO;

public interface UserService {
	
	UserDTO findById(Long id);
	
	List<UserDTO> findAll();
	
	UserDTO save(UserDTO userDTO);
	
	UserDTO update(Long id, UserUpdateDTO userUpdateDTO);
	
	boolean deleteById(Long id);
	
	UserDTO review(Long id, UserReviewDTO userReviewDTO);
	
}
