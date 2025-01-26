package dev.senior.bettertech.service;

import dev.senior.bettertech.model.Assignment;
import dev.senior.bettertech.model.Subject;
import dev.senior.bettertech.repository.AssignmentRepository;
import dev.senior.bettertech.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final SubjectRepository subjectRepository;

    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public Assignment createAssignment(Assignment assignment) {
        // Ensure the Subject reference in Assignment is correct
        if (assignment.getSubject() != null && assignment.getSubject().getId() != null) {
            Subject subject = subjectRepository.findById(assignment.getSubject().getId())
                    .orElseThrow(() -> new RuntimeException("Subject not found"));
            assignment.setSubject(subject);
        }

        // Save the assignment without redundant references
        return assignmentRepository.save(assignment);
    }

    public Assignment updateAssignment(Long id, Assignment updatedAssignment) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        assignment.setTitle(updatedAssignment.getTitle());
        assignment.setDescription(updatedAssignment.getDescription());
        assignment.setDueDate(updatedAssignment.getDueDate());
        return assignmentRepository.save(assignment);
    }

    public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }
}
