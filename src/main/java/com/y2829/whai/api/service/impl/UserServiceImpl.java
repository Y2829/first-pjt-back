package com.y2829.whai.api.service.impl;

import com.y2829.whai.api.entity.User;
import com.y2829.whai.api.repository.UserRepository;
import com.y2829.whai.api.service.UserService;
import com.y2829.whai.common.exception.NotFoundException;
import com.y2829.whai.common.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.y2829.whai.api.dto.UserDto.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public Long modifyUser(PatchUserRequest request, String userOauthId) {
        User user = userRepository.findByUserOauthId(userOauthId)
                .orElseThrow(() -> new NotFoundException("Not found user"));

        if (!user.getId().equals(request.getUserId())) {
            throw new UnauthorizedException("Cannot match a user");
        }

        // TODO 회사 이메일 인증 로직

        return user.getId();
    }

    @Override
    @Transactional
    public Long removeUser(Long userId, String userOauthId) {
        User user = userRepository.findByUserOauthId(userOauthId)
                .orElseThrow(() -> new NotFoundException("Not found user"));

        if (!user.getId().equals(userId)) {
            throw new UnauthorizedException("Cannot match a user");
        }

        userRepository.delete(user);

        return user.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public User findUser(String userOauthId) {
        return userRepository.findByUserOauthId(userOauthId)
                .orElseThrow(() -> new NotFoundException("Not found user"));
    }

}
