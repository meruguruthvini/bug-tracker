package com.bugtracker.bug_tracker.controller;

import com.bugtracker.bug_tracker.model.User;
import com.bugtracker.bug_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user, Model model) {

        User existingUser = userService.findByEmail(user.getEmail());

        if (existingUser != null) {
            model.addAttribute("error", "Email already exists!");
            return "register";
        }

        // ✅ correct method
        userService.save(user);

        model.addAttribute("success", "Account created successfully!");
        return "login";
    }
}
