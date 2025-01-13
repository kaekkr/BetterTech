package dev.senior.bettertech.controller;

import dev.senior.bettertech.model.Role;
import dev.senior.bettertech.model.Subject;
import dev.senior.bettertech.model.User;
import dev.senior.bettertech.repository.SubjectRepository;
import dev.senior.bettertech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    @PostMapping("/subjects")
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        Subject savedSubject = subjectRepository.save(subject);
        return ResponseEntity.ok(savedSubject);
    }

    @PutMapping("/subjects/{id}/assign-teacher")
    public ResponseEntity<String> assignTeacher(@PathVariable Long id, @RequestParam Long teacherId) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        if (!teacher.getRole().equals(Role.ROLE_TEACHER)) {
            return ResponseEntity.badRequest().body("User is not a teacher");
        }

        subject.setTeacher(teacher);
        subjectRepository.save(subject);
        return ResponseEntity.ok("Teacher assigned successfully!");
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully!");
    }
}

