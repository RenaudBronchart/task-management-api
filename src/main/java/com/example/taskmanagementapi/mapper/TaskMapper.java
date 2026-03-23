package com.example.taskmanagementapi.mapper;

import com.example.taskmanagementapi.dto.TaskDto;
import com.example.taskmanagementapi.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDto toDto(Task task) {

        if(task == null) {
            return null;
        }

        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        if (task.getUser() != null) {
            dto.setUserId(task.getUser().getId());
        }

        return dto;
    }
    public Task toEntity(TaskDto dto) {
        if( dto == null) {
            return null;
        }

        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());

        return task;
    }

    public void updateEntity(Task task, TaskDto dto) {
            if (task == null || dto == null) {
        return;
    }

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
}


}
