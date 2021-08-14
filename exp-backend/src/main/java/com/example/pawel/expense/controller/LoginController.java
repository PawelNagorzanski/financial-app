package com.example.pawel.expense.controller;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.pawel.expense.config.JwtTokenProvider;
import com.example.pawel.expense.model.Expense;
import com.example.pawel.expense.model.Role;
import com.example.pawel.expense.model.RoleName;
import com.example.pawel.expense.model.User;
import com.example.pawel.expense.payload.ApiResponse;
import com.example.pawel.expense.payload.LoginRequest;
import com.example.pawel.expense.payload.SignUpRequest;
import com.example.pawel.expense.repository.ExpenseRepository;
import com.example.pawel.expense.repository.RoleRepository;
import com.example.pawel.expense.repository.UserRepository;
import com.example.pawel.expense.response.AppException;
import com.example.pawel.expense.response.JwtAuthenticationResponse;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
	
//    @Autowired
//    UserService userService;
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ExpenseRepository expenseRepository;
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    	
    	System.out.println(loginRequest.getEmail());
    	System.out.println(loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        
        logger.info(jwt);
        
        User user = userRepository.findByEmail(loginRequest.getEmail()); // cahnging return type to User for optional
        Long userId = user.getId();
        
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, userId));
        // return ResponseEntity.ok(new JwtAuthenticationResponse(jwt)).ok(new ApiResponse(true, "User loggged successfully"));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

//        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
//                .orElseThrow(() -> new AppException("User Role not set."));
//
//        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
    
    @GetMapping("/{userId}/expense")
    public ResponseEntity<Expense> getUserExpenses(@PathVariable(value = "userId") Long userId) {
    	List<Expense> expenses = expenseRepository.findExpeseById(userId);
    	    	return new ResponseEntity(expenses, HttpStatus.OK);
    	
    }
    
}
