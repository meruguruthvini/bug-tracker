package com.bugtracker.bug_tracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bugs")
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    private String priority;     // LOW, MEDIUM, HIGH
    private String status;       // OPEN, IN_PROGRESS, CLOSED

    private String assignedTo;   // developer email or name

    public Bug() {}

    public Bug(String title, String description, String priority, String status, String assignedTo) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.assignedTo = assignedTo;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getPriority() { return priority; }

    public void setPriority(String priority) { this.priority = priority; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public String getAssignedTo() { return assignedTo; }

    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
}
