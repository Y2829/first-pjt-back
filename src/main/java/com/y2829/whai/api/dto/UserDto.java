package com.y2829.whai.api.dto;

public class UserDto {

    public static class Info {
        private String userId;
        private String nickname;
    }

    public static class Response {
        private Info info;
    }

}