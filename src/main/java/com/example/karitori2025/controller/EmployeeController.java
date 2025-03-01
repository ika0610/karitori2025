package com.example.karitori2025.controller;

import com.example.karitori2025.model.EmployeeInfo;
import com.example.karitori2025.repository.EmployeeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeInfoRepository employeeInfoRepository;

    // タスクNoに紐づく社員一覧を表示
    @GetMapping("/tasks/{taskNo}/employees")
    public String showEmployeeList(@PathVariable("taskNo") String taskNo, Model model) {
        List<EmployeeInfo> employees = employeeInfoRepository.findByTaskNo(taskNo);
        model.addAttribute("taskNo", taskNo);
        model.addAttribute("employees", employees);
        return "employee_list";
    }

    // 明細(1行)ごとの更新
    @PostMapping("/employees/{id}/update")
    public String updateEmployee(@PathVariable("id") Integer id,
                                 @RequestParam("taskNo") String taskNo,
                                 @RequestParam("employeeDepartment") String employeeDepartment,
                                 @RequestParam("employeeProject") String employeeProject,
                                 @RequestParam("employeeNumber") Integer employeeNumber,
                                 @RequestParam("employeeName") String employeeName,
                                 @RequestParam("answerStatus") String answerStatus,
                                 @RequestParam("answerRemark") String answerRemark) {

        // ID で社員情報を取得
        EmployeeInfo emp = employeeInfoRepository.findById(id).orElse(null);
        if (emp != null) {
            emp.setTaskNo(taskNo);
            emp.setEmployeeDepartment(employeeDepartment);
            emp.setEmployeeProject(employeeProject);
            emp.setEmployeeNumber(employeeNumber);
            emp.setEmployeeName(employeeName);
            emp.setAnswerStatus(answerStatus);
            emp.setAnswerRemark(answerRemark);

            employeeInfoRepository.save(emp);
        }

        // 更新後は一覧に戻る
        return "redirect:https://shiny-guacamole-9w4rq6ppgqwf7qrx-8080.app.github.dev/tasks";
    }
}
