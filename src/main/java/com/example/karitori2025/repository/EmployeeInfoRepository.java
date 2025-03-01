package com.example.karitori2025.repository;

import com.example.karitori2025.model.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Integer> {

    // タスクNo で検索
    List<EmployeeInfo> findByTaskNo(String taskNo);

}
