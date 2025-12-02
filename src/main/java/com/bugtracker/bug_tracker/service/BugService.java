package com.bugtracker.bug_tracker.service;

import com.bugtracker.bug_tracker.model.Bug;
import java.util.List;

public interface BugService {

    // Basic CRUD
    Bug saveBug(Bug bug);

    List<Bug> getAllBugs();

    Bug getBugById(Long id);

    Bug updateBug(Long id, Bug updatedBug);

    void deleteBug(Long id);

    // Search by title and status
    List<Bug> searchBugs(String title, String status);

    // List of bugs assigned to a specific user
    List<Bug> getBugsAssignedTo(String email);

    // ⭐ NEW — Needed for PRIORITY CHART
    List<Bug> searchBugsByPriority(String priority);

    // ⭐ NEW — Needed for STATUS CHART
    int countByStatus(String status);

    // ⭐ NEW — Needed for PRIORITY CHART
    int countByPriority(String priority);

    // ⭐ NEW — Total count
    int countTotal();
}
