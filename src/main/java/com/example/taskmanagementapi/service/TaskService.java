package com.example.taskmanagementapi.service;

import com.example.taskmanagementapi.entity.Task;

import com.example.taskmanagementapi.entity.User;
import com.example.taskmanagementapi.exception.ResourceNotFoundException;
import com.example.taskmanagementapi.repository.TaskRepository;
import com.example.taskmanagementapi.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public TaskService (TaskRepository taskRepository, UserService userService, UserRepository userRepository){
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public Page<Task> getAllTasks(Pageable pageable) {return taskRepository.findAll(pageable);}

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Task not found"))
                ;}

    public Task createTask(Task task, Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found"));
                task.setUser(user);
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("task not found"));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {taskRepository.deleteById(id);}
}
