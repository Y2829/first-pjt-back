package com.y2829.whai.controller;

import com.y2829.whai.common.utils.ApiUtils;
import com.y2829.whai.dto.UserDto;
import com.y2829.whai.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.y2829.whai.common.utils.ApiUtils.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserRestController {

    private final UserService userService;

    @GetMapping("login")
    public ApiUtils.ApiResult<UserDto.Response> login() {
        return success(
                null
        );
    }
}
