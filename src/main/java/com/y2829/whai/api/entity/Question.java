package com.y2829.whai.api.entity;

import com.y2829.whai.api.dto.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static com.y2829.whai.api.dto.QuestionDto.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<QuestionCategory> categories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Image> images;

    private String title;

    private String content;

    private Status status;

    private LocalDateTime createAt = LocalDateTime.now();

    public void update(PatchQuestionRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }

}
