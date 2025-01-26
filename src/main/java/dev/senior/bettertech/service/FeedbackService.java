package dev.senior.bettertech.service;

import dev.senior.bettertech.model.Submission;
import dev.senior.bettertech.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final SubmissionRepository submissionRepository;

    public void approveFeedback(Long submissionId, String feedback) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
        submission.setFeedback(feedback);
        submission.setFeedbackApproved(true);
        submissionRepository.save(submission);
    }
}

