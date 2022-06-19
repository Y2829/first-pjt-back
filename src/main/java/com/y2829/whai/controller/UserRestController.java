package com.y2829.whai.controller;

import com.y2829.whai.common.utils.ApiUtils;
import com.y2829.whai.dto.UserDto;
import com.y2829.whai.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    @Operation(summary = "로그인", description = "로그인합니다.")
    public ApiUtils.ApiResult<UserDto.Response> login() {
        return success(
                null
        );
    }

    @GetMapping("logout")
    @Operation(summary = "로그아웃", description = "로그아웃합니다.")
    public ApiUtils.ApiResult<UserDto.Response> logout() {
        return success(
                null
        );
    }

    @GetMapping
    @Operation(summary = "회원 조회", description = "회원을 조회합니다.")
    public ApiUtils.ApiResult<UserDto.Response> findUser() {
        return success(
                null
        );
    }

    @GetMapping
    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다.")
    public ApiUtils.ApiResult<UserDto.Response> modifyUser() {
        return success(
                null
        );
    }

    @DeleteMapping
    @Operation(summary = "회원 탈퇴", description = "회원을 탈퇴합니다.")
    public ApiUtils.ApiResult<UserDto.Response> removeUser() {
        return success(
                null
        );
    }

}
