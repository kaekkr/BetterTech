package dev.senior.bettertech.controller;

import dev.senior.bettertech.model.Submission;
import dev.senior.bettertech.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final SubmissionService submissionService;

    // Retrieve all submissions for an assignment
    @GetMapping("/assignments/{assignmentId}/submissions")
    public ResponseEntity<List<Submission>> getSubmissions(@PathVariable Long assignmentId) {
        List<Submission> submissions = submissionService.getSubmissionsByAssignment(assignmentId);
        return ResponseEntity.ok(submissions);
    }

    // Approve or update feedback for a submission
    @PutMapping("/submissions/{submissionId}/feedback")
    public ResponseEntity<Submission> updateFeedback(
            @PathVariable Long submissionId,
            @RequestParam String feedback,
            @RequestParam boolean approved) {
        Submission updatedSubmission = submissionService.updateFeedback(submissionId, feedback, approved);
        return ResponseEntity.ok(updatedSubmission);
    }
}
