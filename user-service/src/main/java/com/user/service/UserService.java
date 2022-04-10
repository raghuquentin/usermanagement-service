package com.user.service;

import com.user.model.User;

import java.util.List;

public interface UserService {
    void createUser(User user);
    void updateUser(User user,String email);
    void delete(String email);
    List<User> getAllUsers();
    User getUser(String email);

    boolean authenticate(User user);
}
