package com.y2829.whai.api.service.impl;

import com.y2829.whai.api.entity.Mentor;
import com.y2829.whai.api.entity.Review;
import com.y2829.whai.api.entity.User;
import com.y2829.whai.api.repository.MentorRepository;
import com.y2829.whai.api.repository.ReviewRepository;
import com.y2829.whai.api.repository.UserRepository;
import com.y2829.whai.api.service.ReviewService;
import com.y2829.whai.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.y2829.whai.api.dto.ReviewDto.*;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private static final String NOT_FOUND_USER = "Not Found User!!";
    private static final String NOT_FOUND_REVIEW = "Not Found Review!!";
    private static final String NOT_FOUND_MENTOR = "Not Found Mentor!!";

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MentorRepository mentorRepository;

    @Override
    public Long saveReview(PostReviewRequest requestReview) {

        Review review = requestReview.toEntity();

        User user = userRepository.findById(requestReview.getUserId())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_USER));

        Mentor mentor = mentorRepository.findById(requestReview.getMentorId())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MENTOR));

        review.setUser(user);
        review.setMentor(mentor);

        return reviewRepository.save(review).getId();
    }

    @Override
    public Long modifyReview(PatchReviewRequest request) {

        Review review = reviewRepository.findById(request.getReviewId())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_REVIEW));

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
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_REVIEW));

        reviewRepository.delete(review);

        return review.getId();
    }

}
