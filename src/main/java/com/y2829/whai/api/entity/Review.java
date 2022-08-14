package com.y2829.whai.api.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

import static com.y2829.whai.api.dto.ReviewDto.PatchReviewRequest;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Mentor mentor;

    private Integer grade;

    private String content;

    private LocalDateTime createAt;

    public void update(PatchReviewRequest request) {
        this.content = request.getContent();
        this.grade = request.getGrade();
    }
}
