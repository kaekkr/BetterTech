package dev.senior.bettertech.controller;

import dev.senior.bettertech.model.Submission;
import dev.senior.bettertech.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    // Create a new submission
    @PostMapping("/{assignmentId}")
    public ResponseEntity<Submission> createSubmission(@PathVariable Long assignmentId, @RequestBody Submission submission) {
        Submission savedSubmission = submissionService.createSubmission(assignmentId, submission);
        return ResponseEntity.ok(savedSubmission);
    }

    // Retrieve all submissions for an assignment
    @GetMapping("/{assignmentId}")
    public ResponseEntity<List<Submission>> getSubmissionsByAssignment(@PathVariable Long assignmentId) {
        List<Submission> submissions = submissionService.getSubmissionsByAssignment(assignmentId);
        return ResponseEntity.ok(submissions);
    }

    // Update feedback for a submission
    @PutMapping("/{submissionId}/feedback")
    public ResponseEntity<Submission> updateFeedback(
            @PathVariable Long submissionId,
            @RequestParam String feedback,
            @RequestParam boolean approved) {
        Submission updatedSubmission = submissionService.updateFeedback(submissionId, feedback, approved);
        return ResponseEntity.ok(updatedSubmission);
    }
}
