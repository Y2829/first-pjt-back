package com.y2829.whai.api.controller;

import com.y2829.whai.api.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.y2829.whai.api.dto.QuestionDto.*;
import static com.y2829.whai.common.utils.ApiUtils.*;

@Tag(name = "Question API", description = "질문 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/questions")
public class QuestionRestController {

    private final QuestionService questionService;

    @PostMapping
    @Operation(summary = "질문 등록", description = "질문을 등록합니다.", security = { @SecurityRequirement(name = "bearer-key")})
    public ApiResult<Long> postQuestion(@Valid @RequestBody PostRequest postRequest) {
        return success(
                questionService.saveQuestion(postRequest)
        );
    }

    @PatchMapping
    @Operation(summary = "질문 수정", description = "질문을 수정합니다.", security = { @SecurityRequirement(name = "bearer-key")})
    public ApiResult<Long> putQuestion(@Valid @RequestBody PutRequest putRequest) {
        return success(
                questionService.modifyQuestion(putRequest)
        );
    }

    @DeleteMapping("{userId}/{id}")
    @Operation(summary = "질문 삭제", description = "질문을 삭제합니다.", security = { @SecurityRequirement(name = "bearer-key")})
    public ApiResult<Long> deleteQuestion(@PathVariable Long userId, @PathVariable Long id) {
        return success(
                questionService.removeQuestion(userId, id)
        );
    }

    @GetMapping("detail")
    @Operation(summary = "질문 상세 조회", description = "질문의 상세 정보를 조회합니다.")
    public ApiResult<SimpleQuestion> getQuestion(@PathVariable Long id) {
        return success(
                new SimpleQuestion(questionService.findQuestion(id))
        );
    }

    @GetMapping("all")
    @Operation(summary = "전체 질문 조회", description = "모든 질문을 조회합니다.")
    public ApiResult<PageResponse> getQuestions(Pageable pageable) {
        return success(
                new PageResponse(questionService.findAllQuestion(pageable))
        );
    }

    @GetMapping("my/{userId}")
    @Operation(summary = "나의 질문 조회", description = "나의 모든 질문을 조회합니다.", security = { @SecurityRequirement(name = "bearer-key")})
    public ApiResult<PageResponse> getMyQuestions(@PathVariable Long userId, Pageable pageable) {
        return success(
                new PageResponse(questionService.findAllQuestionByUserId(userId, pageable))
        );
    }

    @GetMapping("category/{categoryId}")
    @Operation(summary = "카테고리별 질문 조회(카테고리 아이디)", description = "해당 카테고리의 질문을 조회합니다.")
    public ApiResult<PageResponse> getQuestionsByCategoryId(@PathVariable Long categoryId, Pageable pageable) {
        return success(
                new PageResponse(questionService.findAllQuestionByCategoryId(categoryId, pageable))
        );
    }

    @GetMapping("category/{subject}")
    @Operation(summary = "카테고리별 질문 조회(카테고리 단어)", description = "해당 카테고리의 질문을 조회합니다.")
    public ApiResult<PageResponse> getQuestionsByCategorySubject(@PathVariable String subject, Pageable pageable) {
        return success(
                new PageResponse(questionService.findAllQuestionByCategorySubject(subject, pageable))
        );
    }

}
