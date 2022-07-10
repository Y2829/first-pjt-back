package com.y2829.whai.api.service.impl;

import com.y2829.whai.api.dto.QuestionDto;
import com.y2829.whai.api.entity.Question;
import com.y2829.whai.api.entity.Category;
import com.y2829.whai.api.entity.Image;
import com.y2829.whai.api.entity.User;
import com.y2829.whai.api.repository.CategoryRepository;
import com.y2829.whai.api.repository.ImageRepository;
import com.y2829.whai.api.repository.QuestionRepository;
import com.y2829.whai.api.repository.UserRepository;
import com.y2829.whai.api.service.QuestionService;
import com.y2829.whai.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    private final CategoryRepository categoryRepository;

    private final ImageRepository imageRepository;

    private final UserRepository userRepository;


    @Override
    public Long saveQuestion(QuestionDto.PostRequest request) {
        Question question = request.toEntity();

        // 유저 매칭
        User user = userRepository.findByUserId(request.getUserId());
        question.setUser(user);

        // 카테고리 매칭
        List<Category> categories = categoryRepository.findAllBySubjectIn(request.getCategories());
        question.setCategories(categories);

        // 이미지 매칭
        List<Image> images = null;
        question.setImages(images);

        return questionRepository.save(question).getId();
    }

    @Override
    public Long removeQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("not found question"));
        questionRepository.delete(question);
        return question.getId();
    }

    @Override
    public Question findQuestion(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("not found question"));
    }

    @Override
    public Page<Question> findAllQuestion(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

}
