package dev.senior.bettertech.service;

import dev.senior.bettertech.model.Submission;
import dev.senior.bettertech.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    public Submission saveSubmission(Submission submission) {
        return submissionRepository.save(submission);
    }

    public List<Submission> getSubmissionsForStudent(Long studentId) {
        return submissionRepository.findByStudentId(studentId);
    }

    public Submission getSubmissionById(Long id) {
        return submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
    }

    public void updateFeedback(Long submissionId, String feedback, boolean approved) {
        Submission submission = getSubmissionById(submissionId);
        submission.setFeedback(feedback);
        submission.setFeedbackApproved(approved);
        submissionRepository.save(submission);
    }
}
