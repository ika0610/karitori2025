package com.example.karitori2025.controller;

import com.example.karitori2025.model.EmployeeInfo;
import com.example.karitori2025.model.EmployeeMasterEditForm;
import com.example.karitori2025.model.Task;
import com.example.karitori2025.repository.EmployeeMasterEditRepository;
import com.example.karitori2025.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeMasterEditController {

    @Autowired
    private EmployeeMasterEditRepository employeeMasterEditRepository;

    @Autowired
    private TaskRepository taskRepository;  // 全タスクを取得するために追加

    // GET /employees/master/edit : 初期表示（空フォーム）
    @GetMapping("/employees/master/edit")
    public String showEmployeeMasterEditForm(Model model) {
        model.addAttribute("employeeMasterEditForm", new EmployeeMasterEditForm());
        return "employee_master_edit";
    }

    // POST /employees/master/edit/load : 社員番号で読み込み
    @PostMapping("/employees/master/edit/load")
    public String loadEmployeeByNumber(@ModelAttribute("employeeMasterEditForm") EmployeeMasterEditForm form,
                                       Model model) {
        if (form.getEmployeeNumber() == null) {
            model.addAttribute("message", "社員番号を入力してください。");
            return "employee_master_edit";
        }

        List<EmployeeInfo> employees = employeeMasterEditRepository.findByEmployeeNumber(form.getEmployeeNumber());
        if (employees.isEmpty()) {
            model.addAttribute("message", "該当社員が存在しません。新規作成します。");
        } else {
            // 1件以上ある場合は、フォームに最初のレコードを反映（参考表示）
            EmployeeInfo first = employees.get(0);
            form.setEmployeeDepartment(first.getEmployeeDepartment());
            form.setEmployeeProject(first.getEmployeeProject());
            form.setEmployeeName(first.getEmployeeName());
            if (employees.size() > 1) {
                model.addAttribute("message", "");
            } else {
                model.addAttribute("message", "社員情報を読み込みました。");
            }
        }
        return "employee_master_edit";
    }

    // POST /employees/master/edit/save : DB保存（新規 or 更新）
    // 新規の場合は、tasks テーブルに存在する全タスク分のレコードを作成し、
    // 同じ社員番号が既に存在する場合は、そのレコードを更新する
    @PostMapping("/employees/master/edit/save")
    public String saveEmployee(@ModelAttribute("employeeMasterEditForm") EmployeeMasterEditForm form,
                               Model model) {
        if (form.getEmployeeNumber() == null) {
            model.addAttribute("message", "社員番号を入力してください。");
            return "employee_master_edit";
        }

        // 全タスクを取得
        List<Task> allTasks = taskRepository.findAll();

        // 全タスクに対して、指定の社員番号でレコードが存在するかチェックし、なければ新規作成、あれば更新
        for (Task t : allTasks) {
            Optional<EmployeeInfo> optEmp = employeeMasterEditRepository.findByTaskNoAndEmployeeNumber(t.getTaskNo(), form.getEmployeeNumber());
            if (optEmp.isPresent()) {
                // 更新: 存在するレコード全てを上書き
                EmployeeInfo emp = optEmp.get();
                emp.setEmployeeDepartment(form.getEmployeeDepartment());
                emp.setEmployeeProject(form.getEmployeeProject());
                emp.setEmployeeName(form.getEmployeeName());
                employeeMasterEditRepository.save(emp);
            } else {
                // 新規作成
                EmployeeInfo newEmp = new EmployeeInfo();
                newEmp.setEmployeeNumber(form.getEmployeeNumber());
                newEmp.setEmployeeDepartment(form.getEmployeeDepartment());
                newEmp.setEmployeeProject(form.getEmployeeProject());
                newEmp.setEmployeeName(form.getEmployeeName());
                // 各タスクの taskNo をセット
                newEmp.setTaskNo(t.getTaskNo());
                employeeMasterEditRepository.save(newEmp);
            }
        }
        model.addAttribute("message", "社員情報を更新しました。");
        return "employee_master_edit";
    }
}
