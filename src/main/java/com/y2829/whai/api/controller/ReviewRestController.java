package com.y2829.whai.api.controller;

import com.y2829.whai.api.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.y2829.whai.api.dto.ReviewDto.*;
import static com.y2829.whai.common.utils.ApiUtils.ApiResult;
import static com.y2829.whai.common.utils.ApiUtils.success;

@Tag(name = "Review API", description = "리뷰 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/review")
public class ReviewRestController {

    private final ReviewService reviewService;

    @PostMapping
    @Operation(summary = "리뷰 등록", description = "리뷰를 등록합니다.", security = { @SecurityRequirement(name = "bearer-key") })
    public ApiResult<Long> createReview(@RequestBody PostReviewRequest requestReview) {
        return success(
            reviewService.saveReview(requestReview)
        );
    }

    @GetMapping("/list")
    @Operation(summary = "전체 리뷰 조회", description = "모든 리뷰를 조회합니다.")
    public ApiResult<ReviewResponse> getReviews(Pageable pageable) {
        return success(
                new ReviewResponse(reviewService.findAll(pageable))
        );
    }

    @PatchMapping
    @Operation(summary = "리뷰 수정", description = "리뷰를 수정합니다.", security = { @SecurityRequirement(name = "bearer-key") })
    public ApiResult<Long> modifyReviews(@Valid @RequestBody PatchReviewRequest request) {
        return success(
                reviewService.modifyReview(request)
        );
    }

    @DeleteMapping("{userId}/{reviewId}")
    @Operation(summary = "리뷰 삭제", description = "리뷰를 삭제합니다.", security = { @SecurityRequirement(name = "bearer-key") })
    public ApiResult<Long> removeReviews(@PathVariable Long userId, @PathVariable Long reviewId) {
        return success(
                reviewService.removeReview(userId, reviewId)
        );
    }

}
