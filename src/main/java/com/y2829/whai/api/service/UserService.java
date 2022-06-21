package com.y2829.whai.api.service;

import com.y2829.whai.api.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> login();

}
