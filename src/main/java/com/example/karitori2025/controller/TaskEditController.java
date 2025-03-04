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
import java.util.List;

@Controller
public class TaskEditController {

    @Autowired
    private TaskEditRepository taskEditRepository;

    // GET /tasks/edit : タスク情報編集画面（空フォーム）
    @GetMapping("/tasks/edit")
    public String showTaskEditForm(Model model) {
        model.addAttribute("taskEditForm", new TaskEditForm());
        return "task_edit";
    }

    // POST /tasks/edit/load : タスクNo で既存タスクを読み込み
    @PostMapping("/tasks/edit/load")
    public String loadTaskByTaskNo(@ModelAttribute("taskEditForm") TaskEditForm form,
                                   Model model) {
        if (form.getTaskNo() == null || form.getTaskNo().isEmpty()) {
            model.addAttribute("message", "タスクNoを入力してください。");
            return "task_edit";
        }

        // タスク番号に一致するタスクを全件取得
        List<Task> tasks = taskEditRepository.findByTaskNo(form.getTaskNo());
        if (!tasks.isEmpty()) {
            // 複数件見つかった場合は最初の1件を利用する
            Task t = tasks.get(0);
            form.setTaskName(t.getTaskName());
            form.setRegistrant(t.getRegistrant());
            form.setDetail(t.getDetail());
            form.setTaskStatus(t.getTaskStatus());
            if (t.getRegistrationDate() != null) {
                form.setRegistrationDateStr(
                    t.getRegistrationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))
                );
            }
            if (tasks.size() > 1) {
                model.addAttribute("message", "");
            } else {
                model.addAttribute("message", "");
            }
        } else {
            model.addAttribute("message", "該当タスクがありません。新規作成します。");
        }
        return "task_edit";
    }

    // POST /tasks/edit/save : タスク情報をDBに保存（新規 or 更新）し、
    // タスク一覧へリダイレクトせずに「タスク情報を更新・追加しました」のメッセージを表示する
    @PostMapping("/tasks/edit/save")
    public String saveTask(@ModelAttribute("taskEditForm") TaskEditForm form,
                           Model model) {
        if (form.getTaskNo() == null || form.getTaskNo().isEmpty()) {
            model.addAttribute("message", "タスクNoを入力してください。");
            return "task_edit";
        }

        // 同じタスク番号のタスクを全件取得し、存在する場合は最初の1件を更新
        List<Task> tasks = taskEditRepository.findByTaskNo(form.getTaskNo());
        Task task;
        if (!tasks.isEmpty()) {
            task = tasks.get(0);
        } else {
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

        taskEditRepository.save(task);

        model.addAttribute("message", "タスク情報を更新・追加しました。");

        // 最新の内容をフォームに反映
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
