package com.y2829.whai.api.service;

import com.y2829.whai.api.dto.ReviewDto;
import com.y2829.whai.api.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

    Long saveReview(ReviewDto.RequestReview requestReview);

    Long modifyReview(ReviewDto.PutRequestReview request);

    Page<Review> findAll(Pageable pageable);

    Long removeReview(Long userId, Long reviewId);
}
