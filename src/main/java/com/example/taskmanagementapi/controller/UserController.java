package com.example.taskmanagementapi.controller;

import com.example.taskmanagementapi.dto.UserDTO;
import com.example.taskmanagementapi.entity.User;
import com.example.taskmanagementapi.mapper.UserMapper;
import com.example.taskmanagementapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {

        List<User> users = userService.getAllUsers();

        List<UserDTO> userDTOs = users.stream()
                .map(userMapper::toDTO)
                .toList();

        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);

        return ResponseEntity.ok(userMapper.toDTO(user));

    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {

        User user = userMapper.toEntity(userDTO);

        User savedUser = userService.createUser(user);

        return ResponseEntity.ok(userMapper.toDTO(savedUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserDTO userDTO
    ) {

        User user = userMapper.toEntity(userDTO);

        User updatedUser = userService.updateUser(id, user);

        return ResponseEntity.ok(userMapper.toDTO(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}