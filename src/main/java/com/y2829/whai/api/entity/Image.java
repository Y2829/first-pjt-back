package com.y2829.whai.api.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image extends BaseEntity {

    @ManyToOne
    private Question question;

    private String storeFileName;

    private String originFileName;

    private LocalDateTime createAt;

}
