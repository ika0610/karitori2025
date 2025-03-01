package com.example.karitori2025.repository;

import com.example.karitori2025.model.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface EmployeeMasterEditRepository extends JpaRepository<EmployeeInfo, Integer> {
    List<EmployeeInfo> findByEmployeeNumber(Integer employeeNumber);

    // 追加：特定のタスクと社員番号でレコードを検索
    Optional<EmployeeInfo> findByTaskNoAndEmployeeNumber(String taskNo, Integer employeeNumber);
}
