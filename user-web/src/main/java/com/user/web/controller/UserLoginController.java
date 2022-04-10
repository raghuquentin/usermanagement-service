package com.user.web.controller;

import com.user.model.User;
import com.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "UserLoginController", description = "Authenticate user to login into the system")
public class UserLoginController {
    @Autowired
    private UserService userService;
    
    @Operation(description = "Validate email id and password")
    @ApiResponses({
    	@ApiResponse(responseCode = "200",description = "User validated successfully"),
    	@ApiResponse(responseCode = "400",description = "Bad request, required parameters not found in the request"),
    	@ApiResponse(responseCode = "401",description = "Invalid emailId or password")
    })
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody @Valid User user){
        userService.authenticate(user);
        return new ResponseEntity<>("User logged in successfully", HttpStatus.ACCEPTED);
    }
}
