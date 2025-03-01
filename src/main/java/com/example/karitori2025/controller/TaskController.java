package com.example.karitori2025.controller;

import com.example.karitori2025.model.Task;
import com.example.karitori2025.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    // タスク一覧表示
    @GetMapping("/tasks")
    public String listTasks(Model model) {
        List<Task> tasks = taskRepository.findTop5ByOrderByIdAsc();
        model.addAttribute("tasks", tasks);
        return "task_list";
    }
}



