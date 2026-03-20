package com.example.taskmanagementapi.controller;

import com.example.taskmanagementapi.dto.TaskDto;
import com.example.taskmanagementapi.service.TaskService;
import com.example.taskmanagementapi.service.TaskServiceImpl;
import com.example.taskmanagementapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController (TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<TaskDto>> getTasks(Pageable pageable) {
        return ResponseEntity.ok(taskService.getAllTasks(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping()
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto taskDto){
        return ResponseEntity.status(201).body(taskService.createTask(taskDto));

    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(
            @Valid @PathVariable Long id,
            @RequestBody TaskDto taskDto) {

    return ResponseEntity.ok(taskService.updateTask(id,taskDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id)
    {
        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }

}
