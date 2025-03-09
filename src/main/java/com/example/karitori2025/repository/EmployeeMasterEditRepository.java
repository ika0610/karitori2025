package com.example.karitori2025.repository;

import com.example.karitori2025.model.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeMasterEditRepository extends JpaRepository<EmployeeInfo, Integer> {
    List<EmployeeInfo> findByEmployeeNumber(Integer employeeNumber);
    List<EmployeeInfo> findByTaskNoAndEmployeeNumber(String taskNo, Integer employeeNumber);
}
