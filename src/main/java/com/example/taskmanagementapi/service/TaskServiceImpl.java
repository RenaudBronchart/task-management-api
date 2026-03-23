package com.example.taskmanagementapi.service;

import com.example.taskmanagementapi.dto.TaskDto;
import com.example.taskmanagementapi.entity.Task;

import com.example.taskmanagementapi.entity.User;
import com.example.taskmanagementapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.taskmanagementapi.exception.ResourceNotFoundException;
import com.example.taskmanagementapi.mapper.TaskMapper;
import com.example.taskmanagementapi.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;
    private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);


    public TaskServiceImpl(TaskRepository taskRepository,UserRepository userRepository, TaskMapper taskMapper){
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public Page<TaskDto> getAllTasks(Pageable pageable) {
        log.info("Fetching all tasks");
        return taskRepository.findAll(pageable)
                .map(taskMapper::toDto);
    }

    @Override
    public TaskDto getTaskById(Long id) {
        log.info("Fetching task with id {}", id);

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Task not found with id {}", id);
                    return new ResourceNotFoundException("Task not found");
                });
        return taskMapper.toDto(task);
    }

    @Override
    public TaskDto createTask(TaskDto taskDto){
        log.info("Creating task with title {}", taskDto.getTitle());

        Task task = taskMapper.toEntity(taskDto);
        User user = userRepository.findById(taskDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        task.setUser(user);
        Task savedTask = taskRepository.save(task);

        log.info("Task created with id {}", savedTask.getId());

        return taskMapper.toDto(savedTask);
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        log.info("Updating task with id {}", id);

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->  {
                    log.warn("Task not found for updated with id {}",id);
                    return new ResourceNotFoundException("Task not found");
                });

        taskMapper.updateEntity(task, taskDto);

        Task updatedTask = taskRepository.save(task);

        log.info("Task updated with id {}", id);

        return taskMapper.toDto(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        log.info("Deleting task with id {}", id);

        if (!taskRepository.existsById(id)) {
            log.warn("Task not found for deletion with id {}", id);
            throw new ResourceNotFoundException("Task not found");
        }

        taskRepository.deleteById(id);
        log.info("Task deleted with id {}", id);
    }

}
