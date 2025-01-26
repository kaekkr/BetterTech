package dev.senior.bettertech.repository;

import dev.senior.bettertech.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findAllByStudents_Id(Long studentId);
}
