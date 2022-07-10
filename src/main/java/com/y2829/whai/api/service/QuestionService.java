package com.y2829.whai.api.service;

import com.y2829.whai.api.dto.QuestionDto;
import com.y2829.whai.api.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionService {

    Question findQuestion(Long id);

    Page<Question> findAllQuestion(Pageable pageable);

    Long saveQuestion(QuestionDto.PostRequest request);

    Long removeQuestion(Long id);

}
