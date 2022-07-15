package com.y2829.whai.api.service.impl;

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
import com.y2829.whai.common.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.y2829.whai.api.dto.QuestionDto.*;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    private final CategoryRepository categoryRepository;

    private final ImageRepository imageRepository;

    private final UserRepository userRepository;

    @Override
    public Long saveQuestion(PostQuestionRequest request) {
        Question question = request.toEntity();

        // 유저 매칭
        User user = userRepository.findById(request.getUserId())
                        .orElseThrow(() -> new NotFoundException("Not found user"));
        question.setUser(user);

        // 카테고리 매칭
        List<String> categories = request.getCategories();
        List<Category> newCategories = new ArrayList<>();

        for (String word : categories) {
            Category category = categoryRepository.findBySubject(word)
                    .orElse(categoryRepository.save(Category.builder().subject(word).build()));

            newCategories.add(category);
        }

        question.setCategories(newCategories);

        // TODO 이미지 매칭
        List<Image> images = null;
        question.setImages(images);

        return questionRepository.save(question).getId();
    }

    @Override
    public Long modifyQuestion(PatchQuestionRequest request) {
        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new NotFoundException("Not found question"));

        // 질문의 작성자가 맞는지 확인
        if (!question.getUser().getId().equals(request.getUserId())) {
            throw new UnauthorizedException("Cannot match a user");
        }

        // 타이틀, 콘텐츠 업데이트
        question.update(request);

        // 카테고리 매칭
        List<Category> categories = question.getCategories();

        Set<String> categorySet = categories.stream()
                .map(Category::getSubject)
                .collect(Collectors.toSet());

        List<String> newCategories = request.getCategories();

        for (String word : newCategories) {
            // 추가되는 카테고리가 있는지 확인
            if (categorySet.contains(word)) continue;

            Category category = categoryRepository.findBySubject(word)
                    .orElse(categoryRepository.save(Category.builder().subject(word).build()));

            categories.add(category);
        }

        // TODO 이미지 매칭
        List<Image> images = null;
        question.setImages(images);

        return questionRepository.save(question).getId();
    }

    @Override
    public Long removeQuestion(Long userId, Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found question"));

        // 질문의 작성자가 맞는지 확인
        if (!question.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Cannot match a user");
        }

        questionRepository.delete(question);
        return question.getId();
    }

    @Override
    public Question findQuestion(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found question"));
    }

    @Override
    public Page<Question> findAllQuestion(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @Override
    public Page<Question> findAllQuestionByUserId(Long userId, Pageable pageable) {
        return questionRepository.findByUserId(userId, pageable);
    }

    @Override
    public Page<Question> findAllQuestionByCategoryId(Long categoryId, Pageable pageable) {
        return questionRepository.findByCategoryId(categoryId, pageable);
    }

    @Override
    public Page<Question> findAllQuestionByCategorySubject(String subject, Pageable pageable) {
        return questionRepository.findByCategorySubject(subject, pageable);
    }

}
