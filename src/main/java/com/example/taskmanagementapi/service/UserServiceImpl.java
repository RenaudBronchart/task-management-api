package com.example.taskmanagementapi.service;

import com.example.taskmanagementapi.dto.UserDto;
import com.example.taskmanagementapi.entity.User;
import com.example.taskmanagementapi.exception.ResourceNotFoundException;
import com.example.taskmanagementapi.mapper.UserMapper;
import com.example.taskmanagementapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
                 }
    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found"));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser); }

    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateEntity(user, userDto);
        User updatedUser = userRepository.save(user);

        return userMapper.toDto(updatedUser);
    }
    @Override
    public void deleteUser(Long id) {userRepository.deleteById(id);}

}
