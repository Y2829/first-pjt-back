package com.y2829.whai.api.repository;

import com.y2829.whai.api.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Optional<Question> findById(Long id);

    Page<Question> findAll(Pageable pageable);

    Page<Question> findByUserId(Long userId, Pageable pageable);

    @Query(value = "SELECT q FROM Question q JOIN QuestionCategory qc ON q = qc.question WHERE qc.category.id = :categoryId")
    Page<Question> findByCategoryId(Long categoryId, Pageable pageable);

    @Query(value = "SELECT q FROM Question q JOIN QuestionCategory qc ON q = qc.question WHERE qc.category.subject = :subject")
    Page<Question> findByCategorySubject(String subject, Pageable pageable);

}
