package com.y2829.whai.service.impl;

import com.y2829.whai.entity.User;
import com.y2829.whai.repository.UserRepository;
import com.y2829.whai.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    @Transactional(readOnly = true)
    public Optional<User> login() {

        // Auth 로그인

        return Optional.empty();
    }
}
