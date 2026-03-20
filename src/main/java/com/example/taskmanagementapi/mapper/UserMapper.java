package com.example.taskmanagementapi.mapper;

import com.example.taskmanagementapi.dto.UserDto;
import com.example.taskmanagementapi.entity.User;
import com.example.taskmanagementapi.exception.ResourceNotFoundException;
import com.example.taskmanagementapi.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());

        return dto;
    }

    public User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        return user;
    }

    public void updateEntity(User user, UserDto dto) {
        if (user == null || dto == null) {
            return;
        }

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
    }
}