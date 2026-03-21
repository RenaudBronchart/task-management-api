package com.example.taskmanagementapi.controller;

import com.example.taskmanagementapi.dto.TaskDto;
import com.example.taskmanagementapi.response.ApiResponse;
import com.example.taskmanagementapi.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<TaskDto>>> getTasks(Pageable pageable) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        taskService.getAllTasks(pageable),
                        "Tasks retrieved successfully",
                        200
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDto>> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        taskService.getTaskById(id),
                        "Task retrieved successfully",
                        200
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TaskDto>> createTask(@Valid @RequestBody TaskDto taskDto) {
        return ResponseEntity.status(201).body(
                new ApiResponse<>(
                        taskService.createTask(taskDto),
                        "Task created successfully",
                        201
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDto>> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskDto taskDto
    ) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        taskService.updateTask(id, taskDto),
                        "Task updated successfully",
                        200
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        null,
                        "Task deleted successfully",
                        200
                )
        );
    }
}