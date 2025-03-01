package com.example.karitori2025.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("loginId") String loginId, Model model) {
        if ("TDC".equals(loginId)) {
            return "redirect:https://shiny-guacamole-9w4rq6ppgqwf7qrx-8080.app.github.dev/tasks";
        } else {
            model.addAttribute("error", "ログインIDが正しくありません。");
            return "login";
        }
    }
}

