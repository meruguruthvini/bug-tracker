package com.bugtracker.bug_tracker.controller;

import com.bugtracker.bug_tracker.model.Bug;
import com.bugtracker.bug_tracker.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class KanbanController {

    @Autowired
    private BugService bugService;

    @GetMapping("/kanban")
    public String kanbanBoard(Model model) {

        List<Bug> open = bugService.searchBugs("", "OPEN");
        List<Bug> inProgress = bugService.searchBugs("", "IN_PROGRESS");
        List<Bug> closed = bugService.searchBugs("", "CLOSED");

        model.addAttribute("openBugs", open);
        model.addAttribute("progressBugs", inProgress);
        model.addAttribute("closedBugs", closed);

        return "kanban";
    }
}
