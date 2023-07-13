package com.hellokoding.auth.service;

import com.hellokoding.auth.model.dto.User;

import java.util.List;

public interface UserService {
    Object createUser(User user);
    List<User> searchUser(String userName,String contactNumber,String emailAddress,String roleName,String name);
    User findById(Integer id);
    Object updateUser(User user);
    Object updateUser(User user,boolean isReset);
}
