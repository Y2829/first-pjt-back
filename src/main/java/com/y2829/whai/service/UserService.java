package com.y2829.whai.service;

import com.y2829.whai.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> login();

}
