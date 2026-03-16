package com.example.taskmanagementapi.controller;

import com.example.taskmanagementapi.dto.TaskDTO;
import com.example.taskmanagementapi.entity.Task;
import com.example.taskmanagementapi.mapper.TaskMapper;
import com.example.taskmanagementapi.mapper.UserMapper;
import com.example.taskmanagementapi.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController (TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks() {

        List<Task> tasks = taskService.getAllTasks();

        List<TaskDTO> taskDTOs = tasks.stream()
                .map(taskMapper::toDTO)
                .toList();

        return ResponseEntity.ok(taskDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(taskMapper.toDTO(task));
    }

    @PostMapping()
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO){

        Task task = taskMapper.toEntity(taskDTO);
        Task savedTask = taskService.createTask(task, taskDTO.getUserId());

        return ResponseEntity.status(201).body(taskMapper.toDTO(savedTask));

    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {

    Task task = taskMapper.toEntity(taskDTO);
    Task updatedTasked = taskService.updateTask(id,task);

    return ResponseEntity.ok(taskMapper.toDTO(updatedTasked));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id)
    {
        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }

}
