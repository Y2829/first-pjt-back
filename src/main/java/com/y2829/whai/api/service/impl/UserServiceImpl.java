package com.y2829.whai.api.service.impl;

import com.y2829.whai.api.entity.User;
import com.y2829.whai.api.repository.UserRepository;
import com.y2829.whai.api.service.UserService;
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
    public Optional<User> getUser(String userOauthId) {
        return Optional.of(userRepository.findByUserOauthId(userOauthId));
    }

}
