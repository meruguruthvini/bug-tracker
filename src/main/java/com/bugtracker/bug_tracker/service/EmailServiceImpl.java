package com.bugtracker.bug_tracker.service.impl;

import com.bugtracker.bug_tracker.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(String to, String subject, String message) {
        // 🚫 EMAIL DISABLED — No mail will be sent
        System.out.println("EMAIL DISABLED → Pretending to send email to: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
    }
}
