package com.bugtracker.bug_tracker.controller;

import com.bugtracker.bug_tracker.model.Bug;
import com.bugtracker.bug_tracker.model.Comment;
import com.bugtracker.bug_tracker.service.BugService;
import com.bugtracker.bug_tracker.service.CommentService;
import com.bugtracker.bug_tracker.service.UserService;
import com.bugtracker.bug_tracker.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class BugController {

    @Autowired
    private BugService bugService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    // Email service kept but email disabled inside implementation
    @Autowired
    private EmailService emailService;

    // LIST ALL BUGS
    @GetMapping("/bugs")
    public String listBugs(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String status,
            Model model,
            Principal principal) {

        String email = principal.getName();
        String role = userService.findByEmail(email).getRole();

        List<Bug> bugs;

        if (role.equals("ADMIN")) {
            bugs = bugService.searchBugs(title, status);
        } else {
            bugs = bugService.getBugsAssignedTo(email);
        }

        model.addAttribute("bugs", bugs);
        model.addAttribute("title", title);
        model.addAttribute("status", status);

        return "bugs";
    }

    // MY BUGS PAGE
    @GetMapping("/bugs/my")
    public String myBugs(Model model, Principal principal) {
        String email = principal.getName();
        List<Bug> myBugs = bugService.getBugsAssignedTo(email);

        model.addAttribute("bugs", myBugs);

        return "bugs";
    }

    // CREATE BUG FORM
    @GetMapping("/bugs/new")
    public String showCreateBugForm(Model model) {
        model.addAttribute("bug", new Bug());
        return "create_bug";
    }

    // SAVE NEW BUG — EMAIL DISABLED SAFELY
    @PostMapping("/bugs")
    public String saveBug(@ModelAttribute("bug") Bug bug) {

        bugService.saveBug(bug);

        // Email call will not send anything because implementation is disabled
        String assignedEmail = bug.getAssignedTo();

        if (assignedEmail != null && assignedEmail.contains("@")) {
            emailService.sendEmail(
                    assignedEmail,
                    "New Bug Assigned",
                    "A new bug was assigned to you."
            );
        }

        return "redirect:/bugs";
    }

    // EDIT PAGE
    @GetMapping("/bugs/edit/{id}")
    public String editBug(@PathVariable Long id, Model model) {
        Bug bug = bugService.getBugById(id);
        model.addAttribute("bug", bug);
        return "edit_bug";
    }

    // UPDATE
    @PostMapping("/bugs/update/{id}")
    public String updateBug(@PathVariable Long id, @ModelAttribute("bug") Bug bug) {
        bugService.updateBug(id, bug);
        return "redirect:/bugs";
    }

    // DELETE
    @GetMapping("/bugs/delete/{id}")
    public String deleteBug(@PathVariable Long id) {
        bugService.deleteBug(id);
        return "redirect:/bugs";
    }

    // BUG DETAILS
    @GetMapping("/bugs/{id}")
    public String viewBugDetails(@PathVariable Long id, Model model) {

        Bug bug = bugService.getBugById(id);
        model.addAttribute("bug", bug);

        model.addAttribute("comments", commentService.getCommentsByBugId(id));

        return "bug_details";
    }

    // ADD COMMENT
    @PostMapping("/bugs/{id}/comments")
    public String addComment(
            @PathVariable Long id,
            @RequestParam String message,
            Principal principal) {

        String email = principal.getName();

        Comment comment = new Comment(id, email, message);
        commentService.saveComment(comment);

        return "redirect:/bugs/" + id;
    }
}
