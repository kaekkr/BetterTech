package dev.senior.bettertech.controller;

import dev.senior.bettertech.model.Assignment;
import dev.senior.bettertech.model.Submission;
import dev.senior.bettertech.repository.AssignmentRepository;
import dev.senior.bettertech.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
    private final AssignmentRepository assignmentRepository;
    private final SubmissionRepository submissionRepository;

    @GetMapping("/assignments")
    public ResponseEntity<List<Assignment>> getAssignments() {
        return ResponseEntity.ok(assignmentRepository.findAll());
    }

    @PostMapping("/assignments/{id}/submit")
    public ResponseEntity<String> submitAssignment(@PathVariable Long id, @RequestBody Submission submission) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        submission.setAssignment(assignment);
        submission.setSubmissionTime(LocalDateTime.now());
        submissionRepository.save(submission);

        return ResponseEntity.ok("Assignment submitted successfully!");
    }
}

