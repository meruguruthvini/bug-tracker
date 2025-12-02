package com.bugtracker.bug_tracker.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activity_logs")
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bugId;
    private String action;     // CREATED, UPDATED, COMMENTED, STATUS CHANGED
    private String performedBy; // Email of user
    private LocalDateTime timestamp;

    public ActivityLog() {}

    public ActivityLog(Long bugId, String action, String performedBy) {
        this.bugId = bugId;
        this.action = action;
        this.performedBy = performedBy;
        this.timestamp = LocalDateTime.now();
    }

    // getters & setters
}
