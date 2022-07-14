package com.y2829.whai.api.service;

import com.y2829.whai.api.dto.UserDto;
import com.y2829.whai.api.entity.User;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserService {

    Long modifyUser(UserDto.PutRequest request, String userOauthId);

    Long removeUser(Long userId, String userOauthId);

    User findUser(String userOauthId);

}
