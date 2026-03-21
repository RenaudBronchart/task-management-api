package com.example.taskmanagementapi.controller;

import com.example.taskmanagementapi.dto.UserDto;
import com.example.taskmanagementapi.entity.User;
import com.example.taskmanagementapi.mapper.UserMapper;
import com.example.taskmanagementapi.response.ApiResponse;
import com.example.taskmanagementapi.service.UserService;
import com.example.taskmanagementapi.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getUsers() {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        userService.getAllUsers(),
                        "Users retrieved successfully",
                        200
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        userService.getUserById(id),
                        "User retrieved successfully",
                        200
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(201).body(
                new ApiResponse<>(
                        userService.createUser(userDto),
                        "User created successfully",
                        201
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        userService.updateUser(id, userDto),
                        "User updated successfully",
                        200
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        null,
                        "User deleted successfully",
                        200
                )
        );
    }
}