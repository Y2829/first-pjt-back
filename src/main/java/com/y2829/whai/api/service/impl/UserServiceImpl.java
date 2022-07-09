package com.y2829.whai.api.service.impl;

import com.y2829.whai.api.dto.UserDto;
import com.y2829.whai.api.entity.User;
import com.y2829.whai.api.repository.UserRepository;
import com.y2829.whai.api.service.UserService;
import com.y2829.whai.common.exception.NotFoundException;
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
    public Optional<User> getUser(String userId) {
        return Optional.of(userRepository.findByUserId(userId));
    }

    @Override
    public Boolean insertUser(UserDto.Join request) {
        User newUser = request.toEntity();
        userRepository.save(newUser);
        return true;
    }

    @Override
    public Boolean updateUser(UserDto.Join request) {
        return null;
    }

    @Override
    public Boolean deleteUser(String userId) {
        return null;
    }

}
