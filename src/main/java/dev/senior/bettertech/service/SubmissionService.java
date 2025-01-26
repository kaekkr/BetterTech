package dev.senior.bettertech.service;

import dev.senior.bettertech.model.Assignment;
import dev.senior.bettertech.model.Submission;
import dev.senior.bettertech.repository.AssignmentRepository;
import dev.senior.bettertech.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;
    private final AIService aiService;

    // Create a new submission
    public Submission createSubmission(Long assignmentId, Submission submission) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        submission.setAssignment(assignment);
        submission = submissionRepository.save(submission);

        // Generate AI feedback
        String feedback = aiService.generateFeedback(submission.getFileUrl());
        submission.setFeedback(feedback);
        submission.setFeedbackApproved(false);

        return submissionRepository.save(submission);
    }

    // Retrieve a specific submission by ID
    public Submission getSubmissionById(Long submissionId) {
        return submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
    }


    // Retrieve all submissions for an assignment
    public List<Submission> getSubmissionsByAssignment(Long assignmentId) {
        return submissionRepository.findByAssignmentId(assignmentId);
    }

    // Approve or update feedback
    public Submission updateFeedback(Long submissionId, String feedback, boolean approved) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        submission.setFeedback(feedback);
        submission.setFeedbackApproved(approved);

        return submissionRepository.save(submission);
    }
}
