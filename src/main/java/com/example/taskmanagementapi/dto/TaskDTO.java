package com.example.taskmanagementapi.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskDTO {

    private Long id;

    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    @NotNull(message = "UsiedId is required")
    private Long userId;

}
