package dev.senior.bettertech.repository;

import dev.senior.bettertech.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
