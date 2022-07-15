package com.y2829.whai.api.dto;

import com.y2829.whai.api.entity.User;
import com.y2829.whai.oauth.entity.ProviderType;
import com.y2829.whai.oauth.entity.RoleType;
import lombok.*;

import javax.validation.constraints.NotNull;

public class UserDto {

    @Getter
    @Setter
    public static class PatchUserRequest {
        @NotNull
        private Long userId;
        @NotNull
        private String companyEmail;
    }

    @Getter
    @Setter
    public static class SimpleUser {
        private final Long userId;
        private final String email;
        private final String name;
        private final String company;
        private final String profileImageUrl;
        private final ProviderType providerType;
        private final RoleType roleType;

        public SimpleUser(User user) {
            this.userId = user.getId();
            this.email = user.getEmail();
            this.name = user.getName();
            this.company = user.getCompany();
            this.profileImageUrl = user.getProfileImageUrl();
            this.providerType = user.getProviderType();
            this.roleType = user.getRoleType();
        }
    }

}
