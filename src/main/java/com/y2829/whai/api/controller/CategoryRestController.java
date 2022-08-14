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
public class CategoryRestController {

    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "모든 카테고리 조회", description = "모든 카테고리를 조회합니다.")
    public ApiResult<ListCategoryResponse> getCategory() {
        return success(
            new ListCategoryResponse(categoryService.findAllCategory())
        );
    }

    @GetMapping("{word}")
    @Operation(summary = "카테고리 검색", description = "카테고리를 검색합니다.")
    public ApiResult<ListCategoryResponse> getCategoriesByWord(
            @PathVariable String word
    ) {
        return success(
                new ListCategoryResponse(categoryService.findAllCategoryByWord(word))
        );
    }

}
