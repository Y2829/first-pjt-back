package com.y2829.whai.api.service.impl;

import com.y2829.whai.api.entity.*;
import com.y2829.whai.api.repository.*;
import com.y2829.whai.api.service.QuestionService;
import com.y2829.whai.common.exception.NotFoundException;
import com.y2829.whai.common.exception.UnauthorizedException;
import com.y2829.whai.common.utils.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.y2829.whai.api.dto.QuestionDto.*;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    private final CategoryRepository categoryRepository;

    private final QuestionCategoryRepository questionCategoryRepository;

    private final ImageRepository imageRepository;

    private final UserRepository userRepository;

    private final S3Uploader s3Uploader;

    @Override
    @Transactional(rollbackFor = Exception.class)
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
                    .orElseGet(() -> categoryRepository.save(Category.builder().subject(word).build()));

            newCategories.add(category);
        }

        Question newQuestion = questionRepository.save(question);

        // TODO 이미지 매칭

        List<Image> images = new ArrayList<>();

        request.getImages().forEach(image -> {
            String storePath = "question/" + newQuestion.getId();
            String randomFileName = UUID.randomUUID().toString();
            s3Uploader.upload(image, storePath, randomFileName);
        });


        question.setImages(images);

        // 연관 관계 매핑
        List<QuestionCategory> questionCategoryList = newCategories.stream()
                .map(category -> new QuestionCategory(newQuestion, category)).toList();

        questionCategoryRepository.saveAll(questionCategoryList);

        return newQuestion.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
        List<QuestionCategory> questionCategoryList = question.getCategories();

        Set<String> categorySet = questionCategoryList.stream()
                .map(QuestionCategory::getCategory)
                .map(Category::getSubject)
                .collect(Collectors.toSet());

        List<String> requestCategories = request.getCategories();
        List<Category> newCategories = new ArrayList<>();

        for (String word : requestCategories) {
            // 추가되는 카테고리가 있는지 확인
            if (categorySet.contains(word)) continue;

            Category category = categoryRepository.findBySubject(word)
                    .orElseGet(() -> categoryRepository.save(Category.builder().subject(word).build()));

            newCategories.add(category);
        }

        // TODO 이미지 매칭
        List<Image> images = null;
        question.setImages(images);

        Question newQuestion = questionRepository.save(question);

        // 연관 관계 매핑
        List<QuestionCategory> newQuestionCategoryList = newCategories.stream()
                .map(category -> new QuestionCategory(newQuestion, category)).toList();

        questionCategoryRepository.saveAll(newQuestionCategoryList);

        // 수정되어 제거된 카테고리 삭제
        Set<String> newCategorySet = new HashSet<>(request.getCategories());

        for (QuestionCategory qc : questionCategoryList) {
            if (!newCategorySet.contains(qc.getCategory().getSubject())) {
                questionCategoryRepository.delete(qc);
            }
        }

        return newQuestion.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(readOnly = true)
    public Question findQuestion(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found question"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Question> findAllQuestion(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Question> findAllQuestionByUserOauthId(String userOauthId, Pageable pageable) {
        return questionRepository.findByUserUserOauthId(userOauthId, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Question> findAllQuestionByCategoryId(Long categoryId, Pageable pageable) {
        return questionRepository.findByCategoryId(categoryId, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Question> findAllQuestionByCategorySubject(String subject, Pageable pageable) {
        return questionRepository.findByCategorySubject(subject, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Question> findAllQuestionByTitle(String title, Pageable pageable) {
        return questionRepository.findByTitleLike(title, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Question> findAllQuestionByContent(String content, Pageable pageable) {
        return questionRepository.findByContentLike(content, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Question> findAllQuestionByUserName(String name, Pageable pageable) {
        return questionRepository.findByUserName(name, pageable);
    }

}
