package com.example.karitori2025.repository;

import com.example.karitori2025.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findTop5ByOrderByIdAsc();
}
