package com.y2829.whai.api.service;

import com.y2829.whai.api.entity.User;

import static com.y2829.whai.api.dto.UserDto.*;

public interface UserService {

    Long modifyUser(PatchUserRequest request, String userOauthId);

    Long removeUser(Long userId, String userOauthId);

    User findUser(String userOauthId);

}
