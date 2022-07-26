package com.y2829.whai.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mentor extends BaseEntity {

    @OneToOne
    private User user;

    private String company;

    private String description;

    private Integer answerCount;

    private LocalDateTime answerTime;

}
