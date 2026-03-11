package com.example.taskmanagementapi.repository;

import com.example.taskmanagementapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long > {

}
