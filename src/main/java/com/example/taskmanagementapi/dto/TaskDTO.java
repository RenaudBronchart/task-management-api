package com.example.taskmanagementapi.dto;


import lombok.Data;

@Data
public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private Long userId;

}
