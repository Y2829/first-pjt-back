package com.y2829.whai.api.entity;

import com.y2829.whai.api.dto.MentorDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;

import static com.y2829.whai.api.dto.MentorDto.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mentor extends BaseEntity {

    @OneToOne
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mentor")
    private List<Review> reviews;

    private String company;

    private String description;

    private Integer answerCount;

    private LocalDateTime answerTime;

    public void update(PatchMentorRequest request) {
        this.company = request.getCompany();
        this.description = request.getDescription();
    }
}
