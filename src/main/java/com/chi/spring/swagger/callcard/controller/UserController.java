package com.chi.spring.swagger.callcard.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chi.spring.swagger.callcard.model.UserDTO;
import com.chi.spring.swagger.callcard.model.UserReviewDTO;
import com.chi.spring.swagger.callcard.model.UserUpdateDTO;
import com.chi.spring.swagger.callcard.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@Tag(name = "User", description = "User CRUD")
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Operation(summary = "Create a new User", tags = { "User", "post" })
	@ApiResponses({
			@ApiResponse(responseCode = "201", content = {
					@Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json") 
			}),
			@ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
	})
	@PostMapping("/create")
	public ResponseEntity<UserDTO> create( @Valid @RequestBody UserDTO userDTO) {
		UserDTO save = userService.save(userDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}

	@Operation(summary = "Retrieve a User by Id",
			   description = "Respond to information such as the application date, record date, membership number, and username.",
			   tags = {"User", "get" }
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) 
	})
	@GetMapping("/read/{id}")
	public ResponseEntity<UserDTO> readById(@PathVariable Long id) {
		UserDTO userDTO = null;
		try {
			userDTO = userService.findById(id);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(userDTO);
	}

	@Operation(summary = "Retrieve all User", tags = { "User", "get", "filter" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "204", description = "There are no User", content = {
					@Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
	})
	@GetMapping("/read")
	public ResponseEntity<List<UserDTO>> readAll() {
		List<UserDTO> userDTOs = userService.findAll();
		if (userDTOs.size() == 0)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userDTOs);

		return ResponseEntity.status(HttpStatus.OK).body(userDTOs);
	}

	@Operation(summary = "Update a User by Id", tags = { "User", "put" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = UserUpdateDTO.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) 
	})
	@PutMapping("/update/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
		UserDTO updateUserDTO = null;
		try {
			updateUserDTO = userService.update(id, userUpdateDTO);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(updateUserDTO);
	}

	@Operation(summary = "Delete a User by Id", tags = { "User", "delete" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
		@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
	})
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<UserDTO> delete(@PathVariable Long id) {
		boolean isisSuccess = userService.deleteById(id);
		if (isisSuccess)
			return ResponseEntity.noContent().build();

		return ResponseEntity.notFound().build();
	}
	
	@Operation(summary = "Review a user membership status by Id",
			   description = "Update membership status. Accept value:VALID|INVALID|CANCELLED|DISABLED|APPROVED|UNDER_REVIEW|NOT_APPROVED.",
			   tags = { "UserReview", "put"}
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = UserReviewDTO.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
	})
	@PutMapping("/review/{id}")
	public ResponseEntity<UserDTO> review(@PathVariable Long id, @Valid @RequestBody UserReviewDTO userReviewDTO) {
		UserDTO reviewUserDTO = null;
		try {
			reviewUserDTO = userService.review(id, userReviewDTO);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(reviewUserDTO);
	}
}
