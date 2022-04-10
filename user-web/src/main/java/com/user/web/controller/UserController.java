package com.user.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.exception.EntityNotFoudByIdException;
import com.user.exception.UniqueDataException;
import com.user.model.User;
import com.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Controller", description = "REST Api endpoints to manipulate the User objects")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Operation(description = "Create User Object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "user created"),
            @ApiResponse(responseCode = "409",description = "dublicate entry"),
            })
    @PostMapping("/createUser")
    public ResponseEntity<Void> createUser(@RequestBody @Valid User user){
    	logger.info("User : {}",user);
    	try {
    		userService.createUser(user);
    	}catch (DataIntegrityViolationException e) {
			throw new UniqueDataException(user.getEmailAddress()+" is already exist.");
		}
    	catch (Exception e) {
			logger.error("error occurred while inserting data: {}, {}",e,e.getMessage());
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @Operation(description = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success|ok")})
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<List<User>>(userService.getAllUsers(),HttpStatus.OK);
    }
    
    @Operation(description = "update users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success|ok"),
            @ApiResponse(responseCode = "204",description = "Updated")})
    @PutMapping("/update")
    public ResponseEntity<Void> updateUser(@RequestBody @Valid User user){
    	logger.info("User : {}",user);
        userService.updateUser(user,user.getEmailAddress());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @Operation(description = "delete user by email Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Deleted successfully")})
    @DeleteMapping("/delete/{emailId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String emailId){
    	logger.info("deleting the user :{}", emailId);
    	try {
        userService.delete(emailId);
    	}catch (EmptyResultDataAccessException e) {
    		logger.error("error : ",e,e.getMessage());
			throw new EntityNotFoudByIdException("No record found to delete for the emailId: "+emailId);
		}
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
