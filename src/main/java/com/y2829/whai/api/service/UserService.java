package com.y2829.whai.api.service;

import com.y2829.whai.api.dto.UserDto;
import com.y2829.whai.api.entity.User;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserService {

    Optional<User> getUser(String userId);

    Boolean insertUser(UserDto.Join request);

    Boolean updateUser(UserDto.Join request);

    Boolean deleteUser(String userId);

}
