package dev.senior.bettertech.controller;

import dev.senior.bettertech.model.Subject;
import dev.senior.bettertech.model.User;
import dev.senior.bettertech.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // Create a new subject
    @PostMapping("/subjects")
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        Subject savedSubject = adminService.createSubject(subject);
        return ResponseEntity.ok(savedSubject);
    }

    // Assign a teacher to a subject
    @PutMapping("/subjects/{id}/assign-teacher")
    public ResponseEntity<String> assignTeacher(@PathVariable Long id, @RequestParam Long teacherId) {
        adminService.assignTeacherToSubject(id, teacherId);
        return ResponseEntity.ok("Teacher assigned successfully!");
    }

    // Delete a user (student/teacher)
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        adminService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully!");
    }

    @PutMapping("/subjects/{id}/assign-student")
    public ResponseEntity<String> assignStudent(@PathVariable Long id, @RequestParam Long studentId) {
        adminService.assignStudentToSubject(id, studentId);
        return ResponseEntity.ok("Student assigned successfully!");
    }
}
