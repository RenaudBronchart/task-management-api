package com.example.taskmanagementapi.service;

import com.example.taskmanagementapi.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {

    Page<TaskDto> getAllTasks(Pageable pageable);

    TaskDto getTaskById(Long id);
    TaskDto createTask(TaskDto taskDto);
    TaskDto updateTask(Long id,TaskDto taskDto);

    void deleteTask(Long id);

}