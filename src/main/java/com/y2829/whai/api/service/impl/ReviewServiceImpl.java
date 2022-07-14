package com.y2829.whai.api.service.impl;

import com.y2829.whai.api.dto.ReviewDto;
import com.y2829.whai.api.entity.Review;
import com.y2829.whai.api.entity.User;
import com.y2829.whai.api.repository.ReviewRepository;
import com.y2829.whai.api.repository.UserRepository;
import com.y2829.whai.api.service.ReviewService;
import com.y2829.whai.common.exception.NotFoundException;
import com.y2829.whai.common.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Override
    public Long saveReview(ReviewDto.RequestReview requestReview) {

        Review review = requestReview.toEntity();

        User user = userRepository.findById(requestReview.getUserId())
                .orElseThrow(() -> new NotFoundException("Not Found User!!"));

        review.setUser(user);

        return reviewRepository.save(review).getId();
    }

    @Override
    public Long modifyReview(ReviewDto.PutRequestReview request) {

        Review review = reviewRepository.findById(request.getReviewId())
                .orElseThrow(() -> new NotFoundException("Cannot find Review"));

        if (!review.getUser().getId().equals(request.getUserId())) {
            throw new UnauthorizedException("Cannot match a user");
        }

        review.update(request);

        return reviewRepository.save(review).getId();
    }

    @Override
    public Page<Review> findAll(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    @Override
    public Long removeReview(Long userId, Long reviewId) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("Cannot Found Review"));

        if (review.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Cannot match a user");
        }

        reviewRepository.delete(review);

        return review.getId();
    }

}
