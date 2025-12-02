package com.bugtracker.bug_tracker.service;

import com.bugtracker.bug_tracker.model.Comment;
import com.bugtracker.bug_tracker.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByBugId(Long bugId) {
        return commentRepository.findByBugIdOrderByCreatedAtAsc(bugId);
    }
}
