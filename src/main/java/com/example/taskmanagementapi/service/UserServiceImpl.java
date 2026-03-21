package com.example.taskmanagementapi.service;

import com.example.taskmanagementapi.dto.UserDto;
import com.example.taskmanagementapi.entity.User;
import com.example.taskmanagementapi.exception.ResourceNotFoundException;
import com.example.taskmanagementapi.mapper.UserMapper;
import com.example.taskmanagementapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Fetching all users");

        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserDto getUserById(Long id) {
        log.info("Fetching user with id {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("User not found with id {}", id);
                    return new ResourceNotFoundException("User not found");
                });

        return userMapper.toDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("Creating user with email {}", userDto.getEmail());

        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);

        log.info("User created with id {}", savedUser.getId());

        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        log.info("Updating user with id {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("User not found for update with id {}", id);
                    return new ResourceNotFoundException("User not found");
                });

        userMapper.updateEntity(user, userDto);
        User updatedUser = userRepository.save(user);

        log.info("User updated with id {}", id);

        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Deleting user with id {}", id);

        if (!userRepository.existsById(id)) {
            log.warn("User not found for deletion with id {}", id);
            throw new ResourceNotFoundException("User not found");
        }

        userRepository.deleteById(id);

        log.info("User deleted with id {}", id);
    }
}