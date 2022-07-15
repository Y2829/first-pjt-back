package com.y2829.whai.api.service;

import com.y2829.whai.api.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.y2829.whai.api.dto.QuestionDto.*;

public interface QuestionService {

    Long saveQuestion(PostQuestionRequest request);

    Long modifyQuestion(PatchQuestionRequest request);

    Long removeQuestion(Long userId, Long id);

    Question findQuestion(Long id);

    Page<Question> findAllQuestion(Pageable pageable);

    Page<Question> findAllQuestionByUserId(Long userId, Pageable pageable);

    Page<Question> findAllQuestionByCategoryId(Long categoryId, Pageable pageable);

    Page<Question> findAllQuestionByCategorySubject(String subject, Pageable pageable);

}
