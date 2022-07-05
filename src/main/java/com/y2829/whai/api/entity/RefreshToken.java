package com.y2829.whai.api.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RefreshToken extends BaseEntity {

    @NotNull
    private String userId;

    @NotNull
    private String token;

}
