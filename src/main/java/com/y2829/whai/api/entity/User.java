package com.y2829.whai.api.entity;

import com.y2829.whai.oauth.entity.ProviderType;
import com.y2829.whai.oauth.entity.RoleType;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseEntity {

    @NotNull
    private String userId;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String profileImageUrl;

    @NotNull
    private ProviderType providerType;

    @NotNull
    private RoleType roleType;

    @NotNull
    private LocalDateTime createdAt;

}
