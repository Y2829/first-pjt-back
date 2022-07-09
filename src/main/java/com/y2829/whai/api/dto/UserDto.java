package com.y2829.whai.api.dto;

import com.y2829.whai.api.entity.User;
import com.y2829.whai.oauth.entity.ProviderType;
import com.y2829.whai.oauth.entity.RoleType;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class UserDto {

    @Setter
    public static class Info {
        @NotNull
        private String userId;
        @NotNull
        private String password;
        @NotNull
        private String name;
        @NotNull
        private String email;
    }

    @Setter
    public static class Join {
        @NotNull
        private Info info;
        private MultipartFile profileImg;

        public User toEntity() {
            return User.builder()
                    .userId(info.userId)
                    // 암호화 로직 추가
                    .password(info.password)
                    .name(info.name)
                    .email(info.email)
                    // S3 로직 추가
                    .providerType(ProviderType.WHAI)
                    .roleType(RoleType.USER)
                    .createdAt(LocalDateTime.now())
                    .build();
        }
    }

    @Setter
    public static class Response {
        private Info info;
        private String profileUrl;
    }

}
