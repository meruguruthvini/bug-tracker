package com.bugtracker.bug_tracker.controller;

import com.bugtracker.bug_tracker.model.Bug;
import com.bugtracker.bug_tracker.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private BugService bugService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {

        String email = principal.getName();

        // -----------------------------
        // MAIN DASHBOARD COUNTS
        // -----------------------------
        int total = bugService.countTotal();
        int open = bugService.countByStatus("OPEN");
        int closed = bugService.countByStatus("CLOSED");
        int inProgress = bugService.countByStatus("IN_PROGRESS");

        // -----------------------------
        // PRIORITY COUNTS (LOW/MED/HIGH)
        // -----------------------------
        int low = bugService.countByPriority("LOW");
        int medium = bugService.countByPriority("MEDIUM");
        int high = bugService.countByPriority("HIGH");

        // -----------------------------
        // BUGS ASSIGNED TO CURRENT USER
        // -----------------------------
        List<Bug> assignedToYou = bugService.getBugsAssignedTo(email);

        // -----------------------------
        // SEND VALUES TO VIEW
        // -----------------------------
        model.addAttribute("totalBugs", total);
        model.addAttribute("openBugs", open);
        model.addAttribute("closedBugs", closed);
        model.addAttribute("inProgressBugs", inProgress);

        model.addAttribute("lowPriority", low);
        model.addAttribute("mediumPriority", medium);
        model.addAttribute("highPriority", high);

        model.addAttribute("assignedList", assignedToYou);

        return "dashboard";
    }
}
