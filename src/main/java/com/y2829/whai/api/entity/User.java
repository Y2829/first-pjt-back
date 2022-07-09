package com.y2829.whai.api.entity;

import com.y2829.whai.oauth.entity.ProviderType;
import com.y2829.whai.oauth.entity.RoleType;
import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseEntity {

    private String userId;

    private String password;

    private String name;

    private String email;

    private String profileImageUrl;

    private ProviderType providerType;

    private RoleType roleType;

    private LocalDateTime createdAt;

}
