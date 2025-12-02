package com.bugtracker.bug_tracker.service;

public interface EmailService {
    void sendEmail(String to, String subject, String message);
}
