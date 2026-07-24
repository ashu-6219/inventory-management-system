package com.ashutosh.inventory.service;

import com.ashutosh.inventory.dto.user.UserRequest;
import com.ashutosh.inventory.dto.user.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long userId);

    UserResponse updateUser(Long userId, UserRequest request);

    void deleteUser(Long userId);
}