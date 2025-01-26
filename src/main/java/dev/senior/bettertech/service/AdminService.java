package dev.senior.bettertech.service;

import dev.senior.bettertech.model.Role;
import dev.senior.bettertech.model.Subject;
import dev.senior.bettertech.model.User;
import dev.senior.bettertech.repository.SubjectRepository;
import dev.senior.bettertech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    // Assign a teacher to a subject
    public void assignTeacherToSubject(Long subjectId, Long teacherId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        if (!teacher.getRole().equals(Role.ROLE_TEACHER)) {
            throw new IllegalArgumentException("User is not a teacher");
        }

        subject.setTeacher(teacher);
        subjectRepository.save(subject);
    }

    public void assignStudentToSubject(Long subjectId, Long studentId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (!student.getRole().equals(Role.ROLE_STUDENT)) {
            throw new IllegalArgumentException("User is not a student");
        }

        subject.getStudents().add(student);
        subjectRepository.save(subject);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
