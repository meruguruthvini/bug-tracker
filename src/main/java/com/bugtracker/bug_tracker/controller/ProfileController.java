package com.bugtracker.bug_tracker.controller;

import com.bugtracker.bug_tracker.model.User;
import com.bugtracker.bug_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String profilePage(Model model, Principal principal) {

        String email = principal.getName();
        User user = userService.findByEmail(email);

        model.addAttribute("user", user);

        return "profile";
    }

    @PostMapping("/profile/upload")
    public String uploadProfilePic(@RequestParam("image") MultipartFile file,
                                   Principal principal) throws IOException {

        if (!file.isEmpty()) {

            String email = principal.getName();
            User user = userService.findByEmail(email);

            String uploadDir;

            // Windows path
            uploadDir = "C:/bugtracker/uploads/profile/";

            new File(uploadDir).mkdirs();  // create directory

            String fileName = user.getId() + ".png";

            File destination = new File(uploadDir + fileName);
            file.transferTo(destination);

            user.setProfilePic(fileName);
            userService.save(user);
        }

        return "redirect:/profile";
    }
}
