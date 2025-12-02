package com.bugtracker.bug_tracker.service;

import com.bugtracker.bug_tracker.model.User;

public interface UserService {

    User findByEmail(String email);

    User save(User user);  // ✅ Needed for profile upload
}
