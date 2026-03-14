package com.example.taskmanagementapi.repository;

import com.example.taskmanagementapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
