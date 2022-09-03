package com.y2829.whai.api.service;

import com.y2829.whai.api.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.y2829.whai.api.dto.QuestionDto.*;

public interface QuestionService {

    Long saveQuestion(PostQuestionRequest request);

    Long modifyQuestion(PatchQuestionRequest request);

    Long removeQuestion(Long userId, Long id);

    Question findQuestion(Long id);

    Page<Question> findAllQuestion(Pageable pageable);

    Page<Question> findAllQuestionByUserOauthId(String userOauthId, Pageable pageable);

    Page<Question> findAllQuestionByCategoryAndTitle(List<String> categories, String title, Pageable pageable);

    Page<Question> findAllQuestionByCategoryAndContent(List<String> categories, String content, Pageable pageable);

    Page<Question> findAllQuestionByCategoryAndUserName(List<String> categories, String userName, Pageable pageable);

    Page<Question> findAllQuestionByCategorySubjects(List<String> categories, Pageable pageable);

    /* deprecated */
    Page<Question> findAllQuestionByCategoryId(Long categoryId, Pageable pageable);

    Page<Question> findAllQuestionByCategorySubject(String subject, Pageable pageable);

    Page<Question> findAllQuestionByTitle(String title, Pageable pageable);

    Page<Question> findAllQuestionByContent(String content, Pageable pageable);

    Page<Question> findAllQuestionByUserName(String name, Pageable pageable);

}
