package com.bugtracker.bug_tracker.repository;

import com.bugtracker.bug_tracker.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBugIdOrderByCreatedAtAsc(Long bugId);
}
