package dev.senior.bettertech.controller;

import dev.senior.bettertech.model.Assignment;
import dev.senior.bettertech.model.Submission;
import dev.senior.bettertech.service.StudentService;
import dev.senior.bettertech.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final SubmissionService submissionService;

    @GetMapping("/assignments")
    public ResponseEntity<List<Assignment>> getAssignments() {
        return ResponseEntity.ok(studentService.getAssignments());
    }

    @PostMapping("/assignments/{id}/submit")
    public ResponseEntity<String> submitAssignment(@PathVariable Long id, @RequestBody Submission submission) {
        studentService.submitAssignment(id, submission);
        return ResponseEntity.ok("Assignment submitted successfully!");
    }

    @GetMapping("/feedback")
    public ResponseEntity<List<Submission>> getFeedbackForStudent(@RequestParam Long studentId) {
        return ResponseEntity.ok(submissionService.getSubmissionsForStudent(studentId));
    }


}
