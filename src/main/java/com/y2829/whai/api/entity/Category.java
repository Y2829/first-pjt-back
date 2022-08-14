package com.y2829.whai.api.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends BaseEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<QuestionCategory> questions;

    private String subject;

}
