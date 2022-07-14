package com.y2829.whai.api.controller;

import com.y2829.whai.api.service.CategoryService;
import com.y2829.whai.common.utils.ApiUtils.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.y2829.whai.api.dto.CategoryDto.*;
import static com.y2829.whai.common.utils.ApiUtils.success;

@Tag(name = "Category API", description = "카테고리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "카테고리 조회", description = "모든 카테고리를 조회합니다.")
    public ApiResult<ListResponse> getQuestions() {
        return success(
            new ListResponse(categoryService.findAllCategory())
        );
    }

    @GetMapping("{word}")
    @Operation(summary = "카테고리 검색", description = "카테고리를 검색합니다.")
    public ApiResult<ListResponse> getQuestionsByWord(@PathVariable String word) {
        return success(
                new ListResponse(categoryService.findAllCategoryByWord(word))
        );
    }

}
