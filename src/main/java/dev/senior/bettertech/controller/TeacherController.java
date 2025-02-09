package dev.senior.bettertech.controller;

import dev.senior.bettertech.dto.AssignmentDTO;
import dev.senior.bettertech.model.Assignment;
import dev.senior.bettertech.model.Subject;
import dev.senior.bettertech.model.Submission;
import dev.senior.bettertech.service.AssignmentService;
import dev.senior.bettertech.service.FeedbackService;
import dev.senior.bettertech.service.SubjectService;
import dev.senior.bettertech.service.SubmissionService;
import dev.senior.bettertech.util.AssignmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final SubmissionService submissionService;
    private final AssignmentService assignmentService;
    private final FeedbackService feedbackService;
    private final SubjectService subjectService;

    @PutMapping("/submissions/{id}/feedback")
    public ResponseEntity<String> approveFeedback(@PathVariable Long id, @RequestParam String feedback) {
        feedbackService.approveFeedback(id, feedback);
        return ResponseEntity.ok("Feedback approved successfully!");
    }

    @PostMapping("/assignments")
    public ResponseEntity<AssignmentDTO> createAssignment(@RequestBody AssignmentDTO assignmentDTO) {
        Assignment savedAssignment = assignmentService.createAssignment(AssignmentMapper.toEntity(assignmentDTO));
        return ResponseEntity.ok(AssignmentMapper.toDTO(savedAssignment));
    }

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
