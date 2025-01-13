package dev.senior.bettertech.controller;

import dev.senior.bettertech.model.Assignment;
import dev.senior.bettertech.model.Submission;
import dev.senior.bettertech.repository.AssignmentRepository;
import dev.senior.bettertech.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final AssignmentRepository assignmentRepository;
    private final SubmissionRepository submissionRepository;

    @PostMapping("/assignments")
    public ResponseEntity<Assignment> createAssignment(@RequestBody Assignment assignment) {
        Assignment savedAssignment = assignmentRepository.save(assignment);
        return ResponseEntity.ok(savedAssignment);
    }

    @GetMapping("/submissions")
    public ResponseEntity<List<Submission>> getSubmissions() {
        return ResponseEntity.ok(submissionRepository.findAll());
    }

    @PostMapping("/submissions/{id}/feedback")
    public ResponseEntity<String> provideFeedback(@PathVariable Long id, @RequestParam String feedback, @RequestParam String grade) {
        Submission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        submission.setFeedback(feedback);
        submission.setGrade(grade);
        submission.setFeedbackApproved(true);
        submissionRepository.save(submission);

        return ResponseEntity.ok("Feedback provided successfully!");
    }
}

