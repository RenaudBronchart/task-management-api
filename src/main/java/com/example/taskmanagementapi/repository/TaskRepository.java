package com.example.taskmanagementapi.repository;

import com.example.taskmanagementapi.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import java.util.Optional;


public interface TaskRepository extends JpaRepository<Task, Long> {

    @EntityGraph(attributePaths = {"user"})
    Page<Task> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"user"})
    Optional<Task> findById(Long id);
}

