package com.example.karitori2025.repository;

import com.example.karitori2025.model.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeMasterEditRepository extends JpaRepository<EmployeeInfo, Integer> {

    // 指定社員番号のレコード全件取得
    List<EmployeeInfo> findByEmployeeNumber(Integer employeeNumber);

    // 指定のタスク番号と社員番号のレコードを取得（複数件あれば全部返す）
    List<EmployeeInfo> findAllByTaskNoAndEmployeeNumber(String taskNo, Integer employeeNumber);
}
