package dev.senior.bettertech.controller;

import dev.senior.bettertech.model.Assignment;
import dev.senior.bettertech.model.Submission;
import dev.senior.bettertech.service.AssignmentService;
import dev.senior.bettertech.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final AssignmentService assignmentService;
    private final SubmissionService submissionService;

    // Get all assignments for a student
    @GetMapping("/assignments")
    public ResponseEntity<List<Assignment>> getAssignments() {
        List<Assignment> assignments = assignmentService.getAllAssignments();
        return ResponseEntity.ok(assignments);
    }

    // Submit an assignment
    @PostMapping("/assignments/{assignmentId}/submit")
    public ResponseEntity<Submission> submitAssignment(@PathVariable Long assignmentId, @RequestBody Submission submission) {
        Submission savedSubmission = submissionService.createSubmission(assignmentId, submission);
        return ResponseEntity.ok(savedSubmission);
    }

    // View feedback for a specific submission
    @GetMapping("/submissions/{submissionId}")
    public ResponseEntity<Submission> getSubmissionFeedback(@PathVariable Long submissionId) {
        Submission submission = submissionService.getSubmissionById(submissionId);
        return ResponseEntity.ok(submission);
    }
}
