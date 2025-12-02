package com.bugtracker.bug_tracker.service;

import com.bugtracker.bug_tracker.model.Bug;
import com.bugtracker.bug_tracker.repository.BugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BugServiceImpl implements BugService {

    @Autowired
    private BugRepository bugRepository;

    // -------------------------------
    // BASIC CRUD METHODS
    // -------------------------------

    @Override
    public Bug saveBug(Bug bug) {
        return bugRepository.save(bug);
    }

    @Override
    public List<Bug> getAllBugs() {
        return bugRepository.findAll();
    }

    @Override
    public Bug getBugById(Long id) {
        return bugRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bug not found: " + id));
    }

    @Override
    public Bug updateBug(Long id, Bug updatedBug) {
        Bug bug = getBugById(id);

        bug.setTitle(updatedBug.getTitle());
        bug.setDescription(updatedBug.getDescription());
        bug.setPriority(updatedBug.getPriority());
        bug.setStatus(updatedBug.getStatus());
        bug.setAssignedTo(updatedBug.getAssignedTo());

        return bugRepository.save(bug);
    }

    @Override
    public void deleteBug(Long id) {
        bugRepository.deleteById(id);
    }

    // -------------------------------
    // SEARCH FILTERS
    // -------------------------------

    @Override
    public List<Bug> searchBugs(String title, String status) {

        boolean titleEmpty = (title == null || title.trim().isEmpty());
        boolean statusEmpty = (status == null || status.trim().isEmpty());

        if (titleEmpty && statusEmpty) {
            return bugRepository.findAll();
        }
        if (!titleEmpty && statusEmpty) {
            return bugRepository.findByTitleContainingIgnoreCase(title);
        }
        if (titleEmpty && !statusEmpty) {
            return bugRepository.findByStatus(status);
        }

        return bugRepository.findByTitleContainingIgnoreCaseAndStatus(title, status);
    }

    @Override
    public List<Bug> getBugsAssignedTo(String email) {
        return bugRepository.findByAssignedTo(email);
    }

    // -------------------------------
    // NEW METHODS FOR DASHBOARD CHARTS
    // -------------------------------

    @Override
    public List<Bug> searchBugsByPriority(String priority) {
        return bugRepository.findByPriority(priority);
    }

    @Override
    public int countByStatus(String status) {
        return bugRepository.countByStatus(status);
    }

    @Override
    public int countByPriority(String priority) {
        return bugRepository.countByPriority(priority);
    }

    @Override
    public int countTotal() {
        return (int) bugRepository.count();
    }
}
