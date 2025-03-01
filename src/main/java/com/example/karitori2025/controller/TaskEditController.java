package com.example.karitori2025.controller;

import com.example.karitori2025.model.Task;
import com.example.karitori2025.model.TaskEditForm;
import com.example.karitori2025.repository.TaskEditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
public class TaskEditController {

    @Autowired
    private TaskEditRepository taskEditRepository;

    // GET /tasks/edit : タスク情報編集画面（空フォーム）
    @GetMapping("/tasks/edit")
    public String showTaskEditForm(Model model) {
        model.addAttribute("taskEditForm", new TaskEditForm());
        return "task_edit"; // task_edit.html を返す
    }

    // POST /tasks/edit/load : タスクNo で既存タスクを読み込み
    @PostMapping("/tasks/edit/load")
    public String loadTaskByTaskNo(@ModelAttribute("taskEditForm") TaskEditForm form,
                                   Model model) {
        if (form.getTaskNo() == null || form.getTaskNo().isEmpty()) {
            model.addAttribute("message", "タスクNoを入力してください。");
            return "task_edit";
        }

        Optional<Task> optTask = taskEditRepository.findByTaskNo(form.getTaskNo());
        if (optTask.isPresent()) {
            Task t = optTask.get();
            form.setTaskName(t.getTaskName());
            form.setRegistrant(t.getRegistrant());
            form.setDetail(t.getDetail());
            form.setTaskStatus(t.getTaskStatus());
            if (t.getRegistrationDate() != null) {
                form.setRegistrationDateStr(
                    t.getRegistrationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))
                );
            }
            model.addAttribute("message", "既存タスクを読み込みました。");
        } else {
            model.addAttribute("message", "該当タスクがありません。新規作成します。");
        }
        return "task_edit";
    }

    // POST /tasks/edit/save : タスク情報を DB に保存（新規 or 更新）し、
    // タスク一覧へ遷移せずに「タスク情報を更新・追加しました」のメッセージを表示する
    @PostMapping("/tasks/edit/save")
    public String saveTask(@ModelAttribute("taskEditForm") TaskEditForm form,
                           Model model) {
        if (form.getTaskNo() == null || form.getTaskNo().isEmpty()) {
            model.addAttribute("message", "タスクNoを入力してください。");
            return "task_edit";
        }

        Optional<Task> optTask = taskEditRepository.findByTaskNo(form.getTaskNo());
        Task task;
        if (optTask.isPresent()) {
            // 既存タスクの場合は更新
            task = optTask.get();
        } else {
            // 新規作成の場合
            task = new Task();
        }

        task.setTaskNo(form.getTaskNo());
        task.setTaskName(form.getTaskName());
        task.setRegistrant(form.getRegistrant());
        task.setDetail(form.getDetail());
        task.setTaskStatus(form.getTaskStatus());

        // 登録日時の処理
        if (form.getRegistrationDateStr() != null && !form.getRegistrationDateStr().isEmpty()) {
            LocalDateTime dt = LocalDateTime.parse(
                form.getRegistrationDateStr(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
            );
            task.setRegistrationDate(dt);
        } else {
            if (task.getRegistrationDate() == null) {
                task.setRegistrationDate(LocalDateTime.now());
            }
        }

        // タスクを DB に保存
        taskEditRepository.save(task);

        // 保存後、同じ task_edit.html を再表示し、メッセージを出力
        model.addAttribute("message", "タスク情報を更新・追加しました");

        // 必要に応じて、フォームに最新の内容を反映させる
        form.setTaskName(task.getTaskName());
        form.setRegistrant(task.getRegistrant());
        form.setDetail(task.getDetail());
        form.setTaskStatus(task.getTaskStatus());
        if (task.getRegistrationDate() != null) {
            form.setRegistrationDateStr(
                task.getRegistrationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))
            );
        }
        model.addAttribute("taskEditForm", form);

        return "task_edit";
    }
}
