package com.bugtracker.bug_tracker.repository;

import com.bugtracker.bug_tracker.model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BugRepository extends JpaRepository<Bug, Long> {

    // Search by title
    List<Bug> findByTitleContainingIgnoreCase(String title);

    // Search by status
    List<Bug> findByStatus(String status);

    // Search by title + status
    List<Bug> findByTitleContainingIgnoreCaseAndStatus(String title, String status);

    // Bugs assigned to a developer
    List<Bug> findByAssignedTo(String assignedTo);

    // ⭐ NEW — Priority search (needed for charts)
    List<Bug> findByPriority(String priority);

    // ⭐ NEW — Count bugs by status (OPEN, IN_PROGRESS, CLOSED)
    int countByStatus(String status);

    // ⭐ NEW — Count bugs by priority (LOW, MEDIUM, HIGH)
    int countByPriority(String priority);
}
