package com.example.taskmanagementapi.service;

import com.example.taskmanagementapi.entity.Task;

import com.example.taskmanagementapi.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService ( TaskRepository taskRepository){ this.taskRepository = taskRepository;}

    public List<Task> getAllTasks() {return taskRepository.findAll();}
    public Optional<Task> getTaskById(Long id) {return taskRepository.findById(id);}
    public Task createTask(Task task){ return taskRepository.save(task);}

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("task not found"));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {taskRepository.deleteById(id);}
}
