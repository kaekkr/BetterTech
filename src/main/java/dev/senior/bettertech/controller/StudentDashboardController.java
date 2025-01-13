package dev.senior.bettertech.controller;

import dev.senior.bettertech.model.Assignment;
import dev.senior.bettertech.repository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/student/dashboard")
@RequiredArgsConstructor
public class StudentDashboardController {
    private final AssignmentRepository assignmentRepository;

    @GetMapping
    public ResponseEntity<List<Assignment>> getUpcomingAssignments(@RequestParam Long studentId) {
        List<Assignment> assignments = assignmentRepository.findAll(); // Customize query for student
        return ResponseEntity.ok(assignments);
    }
}

