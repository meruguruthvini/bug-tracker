package com.bugtracker.bug_tracker.service.impl;

import com.bugtracker.bug_tracker.model.ActivityLog;
import com.bugtracker.bug_tracker.repository.ActivityLogRepository;
import com.bugtracker.bug_tracker.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {

    @Autowired
    private ActivityLogRepository repo;

    @Override
    public void log(Long bugId, String action, String performedBy) {
        repo.save(new ActivityLog(bugId, action, performedBy));
    }

    @Override
    public List<ActivityLog> getLogs(Long bugId) {
        return repo.findByBugIdOrderByTimestampDesc(bugId);
    }
}
