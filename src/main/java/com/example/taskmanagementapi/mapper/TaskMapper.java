package com.example.taskmanagementapi.mapper;

import com.example.taskmanagementapi.dto.TaskDTO;
import com.example.taskmanagementapi.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDTO toDTO(Task task) {

        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        if (task.getUser() != null) {
            dto.setUserId(task.getUser().getId());
        }

        return dto;
    }
    public Task toEntity(TaskDTO dto) {

        Task task = new Task();
        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());

        return task;
    }


}
