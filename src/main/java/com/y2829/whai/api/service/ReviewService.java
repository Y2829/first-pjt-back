package com.y2829.whai.api.service;

import com.y2829.whai.api.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.y2829.whai.api.dto.ReviewDto.*;

public interface ReviewService {

    Long saveReview(PostReviewRequest requestReview);

    Long modifyReview(PatchReviewRequest request);

    Page<Review> findAll(Pageable pageable);

    Page<Review> findReviewByMentorId(Long mentorId, Pageable pageable);

    Long removeReview(Long userId, Long reviewId);
}
