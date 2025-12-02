package com.bugtracker.bug_tracker.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bugId;

    private String userEmail;

    @Column(length = 2000)
    private String message;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Comment() {}

    public Comment(Long bugId, String userEmail, String message) {
        this.bugId = bugId;
        this.userEmail = userEmail;
        this.message = message;
    }

    public Long getId() { return id; }

    public Long getBugId() { return bugId; }
    public void setBugId(Long bugId) { this.bugId = bugId; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
