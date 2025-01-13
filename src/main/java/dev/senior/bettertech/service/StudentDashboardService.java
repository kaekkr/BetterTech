package dev.senior.bettertech.service;

import dev.senior.bettertech.model.Assignment;
import dev.senior.bettertech.repository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentDashboardService {
    private final AssignmentRepository assignmentRepository;

    public List<Assignment> getUpcomingAssignments(Long studentId) {
        return assignmentRepository.findAll(); // Customize query as needed
    }
}
