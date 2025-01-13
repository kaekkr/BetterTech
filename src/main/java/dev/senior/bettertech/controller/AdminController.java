package dev.senior.bettertech.controller;

import dev.senior.bettertech.model.Subject;
import dev.senior.bettertech.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/subjects")
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        return ResponseEntity.ok(adminService.createSubject(subject));
    }

    @PutMapping("/subjects/{id}/assign-teacher")
    public ResponseEntity<String> assignTeacher(@PathVariable Long id, @RequestParam Long teacherId) {
        adminService.assignTeacher(id, teacherId);
        return ResponseEntity.ok("Teacher assigned successfully!");
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully!");
    }
}
