package com.example.karitori2025.repository;

import com.example.karitori2025.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskEditRepository extends JpaRepository<Task, Integer> {
    // タスクNo で検索 (編集時に使用)
    List<Task> findByTaskNo(String taskNo);
}

