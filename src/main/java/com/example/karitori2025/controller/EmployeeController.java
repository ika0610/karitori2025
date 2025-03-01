package com.example.karitori2025.controller;

import com.example.karitori2025.model.EmployeeInfo;
import com.example.karitori2025.repository.EmployeeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeInfoRepository employeeInfoRepository;

    /**
     * タスクNoに紐づく社員情報だけを表示する
     */
    @GetMapping("/tasks/{taskNo}/employees")
    public String showEmployeeList(@PathVariable("taskNo") String taskNo, Model model) {
        // タスクNo で社員情報を絞り込み
        List<EmployeeInfo> employees = employeeInfoRepository.findByTaskNo(taskNo);
        model.addAttribute("taskNo", taskNo);
        model.addAttribute("employees", employees);
        return "employee_list";
    }

    /**
     * 回答状況更新
     */
    @PostMapping("/employees/{id}/update")
    public String updateEmployee(@PathVariable("id") Integer id,
                                 @RequestParam("taskNo") String formTaskNo,
                                 @RequestParam("employeeDepartment") String employeeDepartment,
                                 @RequestParam("employeeProject") String employeeProject,
                                 @RequestParam("employeeNumber") Integer employeeNumber,
                                 @RequestParam("employeeName") String employeeName,
                                 @RequestParam("answerStatus") String answerStatus,
                                 @RequestParam("answerRemark") String answerRemark,
                                 Model model) {
        Optional<EmployeeInfo> opt = employeeInfoRepository.findById(id);
        if (opt.isPresent()) {
            EmployeeInfo emp = opt.get();
            // DB上の社員が持つ taskNo と フォームの taskNo が一致する場合のみ更新
            if (emp.getTaskNo() != null && emp.getTaskNo().equals(formTaskNo)) {
                emp.setEmployeeDepartment(employeeDepartment);
                emp.setEmployeeProject(employeeProject);
                emp.setEmployeeNumber(employeeNumber);
                emp.setEmployeeName(employeeName);
                emp.setAnswerStatus(answerStatus);
                emp.setAnswerRemark(answerRemark);
                employeeInfoRepository.save(emp);
                model.addAttribute("updateMessage", "回答状況を更新しました");
            } else {
                model.addAttribute("updateMessage", "タスクNo が一致しないため更新されませんでした");
            }
        } else {
            model.addAttribute("updateMessage", "該当社員情報が見つかりませんでした");
        }

        // 更新後、再度このタスクNo に紐づく社員情報だけ取得
        List<EmployeeInfo> employees = employeeInfoRepository.findByTaskNo(formTaskNo);
        model.addAttribute("taskNo", formTaskNo);
        model.addAttribute("employees", employees);
        return "employee_list";
    }
}
