package dev.senior.bettertech.service;

import dev.senior.bettertech.model.Assignment;
import dev.senior.bettertech.model.Submission;
import dev.senior.bettertech.repository.AssignmentRepository;
import dev.senior.bettertech.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final AssignmentRepository assignmentRepository;
    private final SubmissionRepository submissionRepository;

    public List<Assignment> getAssignments() {
        return assignmentRepository.findAll();
    }

    public void submitAssignment(Long assignmentId, Submission submission) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        submission.setAssignment(assignment);
        submission.setSubmissionTime(LocalDateTime.now());
        submissionRepository.save(submission);
    }
}
