package com.ashutosh.inventory.service.impl;

import com.ashutosh.inventory.constants.MessageConstants;
import com.ashutosh.inventory.dto.user.UserRequest;
import com.ashutosh.inventory.dto.user.UserResponse;
import com.ashutosh.inventory.entity.User;
import com.ashutosh.inventory.exception.ResourceAlreadyExistsException;
import com.ashutosh.inventory.exception.ResourceNotFoundException;
import com.ashutosh.inventory.mapper.UserMapper;
import com.ashutosh.inventory.repository.UserRepository;
import com.ashutosh.inventory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResourceAlreadyExistsException(
                    MessageConstants.USERNAME_ALREADY_EXISTS + request.getUsername());
        }

        if (request.getEmail() != null &&
                userRepository.existsByEmail(request.getEmail())) {

            throw new ResourceAlreadyExistsException(
                    MessageConstants.EMAIL_ALREADY_EXISTS + request.getEmail());
        }

        User user = userMapper.toEntity(request);

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(Long userId) {

        User user = findUserById(userId);

        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse updateUser(Long userId, UserRequest request) {

        User user = findUserById(userId);

        if (!user.getUsername().equals(request.getUsername())
                && userRepository.existsByUsername(request.getUsername())) {

            throw new ResourceAlreadyExistsException(
                    MessageConstants.USERNAME_ALREADY_EXISTS + request.getUsername());
        }

        if (request.getEmail() != null
                && !Objects.equals(user.getEmail(), request.getEmail())
                && userRepository.existsByEmail(request.getEmail())) {

            throw new ResourceAlreadyExistsException(
                    MessageConstants.EMAIL_ALREADY_EXISTS + request.getEmail());
        }

        userMapper.updateEntity(user, request);

        User updatedUser = userRepository.save(user);

        return userMapper.toResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {

        User user = findUserById(userId);

        userRepository.delete(user);
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageConstants.USER_NOT_FOUND + userId));
    }
}