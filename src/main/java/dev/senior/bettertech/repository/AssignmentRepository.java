package dev.senior.bettertech.repository;

import dev.senior.bettertech.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
