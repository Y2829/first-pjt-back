package com.y2829.whai.api.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class QuestionCategory extends BaseEntity {

    @ManyToOne
    private Question question;

    @ManyToOne
    private Category category;

}
