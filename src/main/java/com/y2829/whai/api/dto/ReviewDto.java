package com.y2829.whai.api.dto;

import com.y2829.whai.api.entity.Review;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewDto {

    @Setter
    @Getter
    public static class RequestReview {

        private Long userId;
        private Long mentorId;
        private Integer grade;
        private String content;

        public Review toEntity() {
            return Review.builder()
                    .grade(grade)
                    .content(content)
                    .createAt(LocalDateTime.now())
                    .build();
        }
    }

    @Getter
    @Setter
    public static class PutRequestReview {
        private Long reviewId;
        private Long userId;
        private Long mentorId;
        private Integer grade;
        private String content;
    }

    @Getter
    @Setter
    public static class ViewReview {

        private Long userId;
        private Long mentorId;
        private Integer grade;
        private String content;
        public ViewReview(Review review) {

            this.userId = review.getUser().getId();
            this.mentorId = review.getMentor().getId();
            this.grade = review.getGrade();
            this.content = review.getContent();

        }
    }

    @Getter
    @Setter
    public static class ReviewResponse {
        private Page<ViewReview> reviews;
        public ReviewResponse(Page<Review> reviews) {

            List<ViewReview> allReview = reviews.stream()
                    .map(ViewReview::new)
                    .collect(Collectors.toList());

            this.reviews = new PageImpl<>(allReview);
        }
    }

}
