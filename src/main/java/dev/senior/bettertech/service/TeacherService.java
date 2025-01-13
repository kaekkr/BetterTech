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
public class TeacherService {
    private final AssignmentRepository assignmentRepository;
    private final SubmissionRepository submissionRepository;

    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public void provideFeedback(Long id, String feedback, String grade) {
        Submission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        submission.setFeedback(feedback);
        submission.setGrade(grade);
        submission.setFeedbackApproved(true);
        submissionRepository.save(submission);
    }
}
