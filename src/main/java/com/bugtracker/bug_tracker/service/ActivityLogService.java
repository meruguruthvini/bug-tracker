package com.bugtracker.bug_tracker.service;

import com.bugtracker.bug_tracker.model.ActivityLog;
import java.util.List;

public interface ActivityLogService {
    void log(Long bugId, String action, String performedBy);
    List<ActivityLog> getLogs(Long bugId);
}
