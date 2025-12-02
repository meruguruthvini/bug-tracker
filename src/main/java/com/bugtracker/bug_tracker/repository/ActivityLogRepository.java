package com.bugtracker.bug_tracker.repository;

import com.bugtracker.bug_tracker.model.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
    List<ActivityLog> findByBugIdOrderByTimestampDesc(Long bugId);
}
