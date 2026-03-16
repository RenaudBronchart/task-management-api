package com.example.taskmanagementapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    @NotBlank(message ="Name is required")
    private String name;
    @Email(message= "Email must be valid")
    @NotBlank(message="Email is required")
    private String email;

}
