package com.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.data.repository.UserRepository;
import com.user.exception.InvalidCredentialsException;
import com.user.model.User;
import com.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository repository;
    @Override
    public void createUser(User user) {
        repository.save(user);
    }

    @Override
    public void updateUser(User user, String email) {
        repository.save(user);
    }

    @Override
    public void delete(String email) {
        repository.deleteById(email);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> user = new ArrayList<>();
        repository.findAll().forEach(user::add);
        return user;
    }

    @Override
    public User getUser(String email) {
        Optional<User> optUser = repository.findById(email);
        if(optUser.isPresent())
            return optUser.get();
        else
            return null;
    }

    @Override
    public boolean authenticate(User user) {
    	String emailAddress = user.getEmailAddress();
    	logger.info("user :: emailId :{}, password:{}",emailAddress, user.getPassword());
		Optional<User> userObj = repository.findByEmailAddressAndPassword(emailAddress, user.getPassword());
		logger.info("user is present :{}",userObj.isPresent());
		if (userObj.isPresent()) {
			repository.setLastLoginTime(emailAddress);
		}else {
			throw new InvalidCredentialsException("Invalid credentials, email or password is wrong");
		}
		 
        return true;
    }
}
