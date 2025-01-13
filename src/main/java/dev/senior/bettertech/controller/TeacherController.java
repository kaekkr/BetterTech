package dev.senior.bettertech.controller;

import dev.senior.bettertech.model.Assignment;
import dev.senior.bettertech.model.Submission;
import dev.senior.bettertech.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping("/assignments")
    public ResponseEntity<Assignment> createAssignment(@RequestBody Assignment assignment) {
        Assignment savedAssignment = teacherService.createAssignment(assignment);
        return ResponseEntity.ok(savedAssignment);
    }

    @GetMapping("/submissions")
    public ResponseEntity<List<Submission>> getSubmissions() {
        return ResponseEntity.ok(teacherService.getAllSubmissions());
    }

    @PostMapping("/submissions/{id}/feedback")
    public ResponseEntity<String> provideFeedback(@PathVariable Long id, @RequestParam String feedback, @RequestParam String grade) {
        teacherService.provideFeedback(id, feedback, grade);
        return ResponseEntity.ok("Feedback provided successfully!");
    }
}
