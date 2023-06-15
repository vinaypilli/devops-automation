package com.teamcomputers.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.teamcomputers.config.JwtTokenUtil;
import com.teamcomputers.dto.NewUserFilterDto;
import com.teamcomputers.dto.SubCategoryFilterDto;
import com.teamcomputers.dto.UserFilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.JwtRequest;
import com.teamcomputers.model.JwtResponse;
import com.teamcomputers.model.UserDao;
import com.teamcomputers.model.UserDto;
import com.teamcomputers.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	private String message;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		System.out.println(userDetails + "sdddddddddddddddddddddd");
		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@GetMapping("/userinfo/{username}")
	private UserDao getByUsername(@PathVariable String username) {
		return userDetailsService.getUserByUsername(username);
	}
	@GetMapping("users/active/{department}")
	public List<UserFilterDto> getAllActiveSubcategoriesByCategoryId(@PathVariable int department) {
		return userDetailsService.getAllActiveUsersByDepartmentId(department);
	}

//	@GetMapping("/users/{userId}")
//	private UserDao getById(@PathVariable int userId) {
//		return userDetailsService.getById(userId);
//	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorMessage> handleLocation1(ResourceNotFoundException rx) {
		ErrorMessage errorMessage = new ErrorMessage("USER NOT FOUND", rx.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}

	@GetMapping("/users")
	private List<UserDao> getAll() {
		return userDetailsService.getAll();
	}

	@GetMapping("users/active")
	public List<UserFilterDto> getActiveUsers() {
		return userDetailsService.getActiveUsers();
	}
	

	@DeleteMapping("/users/{userId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable int userId) {
		try {
			userDetailsService.deleteById(userId);
			message = "User Details successfully deleted !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "User details are not deleted" + e.getCause().getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> saveUser(@RequestBody UserDto user) throws Exception {

		try {

			this.userDetailsService.save(user);
			message = "User  successfully registerd !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}

//		catch (DuplicateKeyException e) {
//			//System.out.println(e + "duplivcate....");
//			message = "data is duplicate"+e.getCause().getMessage();
//			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//			} 
		catch (Exception e) {
			message = "User  not registered";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@PutMapping("/users/{userId}")
	public ResponseEntity<ResponseMessage> updateUser(@PathVariable("userId") int userId,
			@Valid @RequestBody UserDto userDto) {
		userDto.setUserId(userId);
		try {
			userDetailsService.updateUser(userDto);
			return ResponseEntity.ok(new ResponseMessage("User details successfully updated"));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("User not found"));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseMessage("Failed to update user details"));
		}
	}

}
