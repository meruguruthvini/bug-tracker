package com.bugtracker.bug_tracker.service;

import com.bugtracker.bug_tracker.model.Comment;
import java.util.List;

public interface CommentService {

    void saveComment(Comment comment);

    List<Comment> getCommentsByBugId(Long bugId);

}
