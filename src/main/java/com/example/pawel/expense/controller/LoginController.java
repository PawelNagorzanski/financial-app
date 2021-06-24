package com.example.pawel.expense.controller;

import com.example.pawel.expense.config.JwtTokenProvider;
import com.example.pawel.expense.model.Role;
import com.example.pawel.expense.model.RoleName;
import com.example.pawel.expense.model.User;
import com.example.pawel.expense.payload.ApiResponse;
import com.example.pawel.expense.payload.LoginRequest;
import com.example.pawel.expense.payload.SignUpRequest;
import com.example.pawel.expense.repository.RoleRepository;
import com.example.pawel.expense.repository.UserRepository;
import com.example.pawel.expense.response.AppException;
import com.example.pawel.expense.response.JwtAuthenticationResponse;
import com.example.pawel.expense.service.UserService;

import jdk.internal.org.jline.utils.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

@Controller

@RequestMapping("/api/auth/")
// @CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

	@Autowired
	UserService userService;

	RoleRepository roleRepository;
	PasswordEncoder passwordEncoder;
	UserRepository userRepository;
	JwtTokenProvider jwtTokenProvider;
	AuthenticationManager authenticationManager;

	@PostMapping("/singin")
	// @CrossOrigin(origins = "*", allowedHeaders = "*") @CrossOrigin(origins = "http://localhost:8080")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtTokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@PostMapping("/singup")
	// @CrossOrigin(origins = "*", allowedHeaders = "*") @CrossOrigin(origins = "http://localhost:8080")
	// @CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity(new ApiResponse(false, "This username is taken!"), HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity(new ApiResponse(false, "This email is taken!"), HttpStatus.BAD_REQUEST);
		}

		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				signUpRequest.getPassword());

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new AppException("Cannot set user role"));
		user.setRoles(Collections.singleton(userRole));
		User result = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();
		System.out.println("Hej Paweł!");
		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	}

	// Old login controller
	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		System.out.println("registration - get");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByUserName(user.getUsername());
		System.out.println("registration - post 1");

		if (userExists != null) {
			bindingResult.rejectValue("userName", "error.user",
					"There is already a user registered with the user name provided");
		}
		if (bindingResult.hasErrors()) {
			List listErr = new ArrayList();
			listErr = bindingResult.getAllErrors();
			for (Object element : listErr) {
				System.out.println("My_Err: " + element);
			}
		} else {
			modelAndView.addObject("user",
					new User(user.getName(), user.getUsername(), user.getEmail(), user.getPassword()));
			System.out.println("registration - post 2 user.getName(): " + user.getName());
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.setViewName("register");

		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		modelAndView.addObject("userName",
				"Welcome " + user.getUsername() + "/" + user.getName() + " " + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}

}
