package com.y2829.whai.api.controller;

import com.y2829.whai.api.entity.User;
import com.y2829.whai.common.exception.NotFoundException;
import com.y2829.whai.common.utils.ApiUtils;
import com.y2829.whai.api.dto.UserDto;
import com.y2829.whai.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.y2829.whai.common.utils.ApiUtils.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserRestController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "회원 조회", description = "회원을 조회합니다.")
    public ApiUtils.ApiResult<User> findUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return success(
                userService.getUser(principal.getUsername())
                        .orElseThrow(() -> new NotFoundException("no user found"))
        );
    }

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

    @PostMapping
    @Operation(summary = "회원 가입", description = "회원가입을 합니다.")
    public ApiUtils.ApiResult<UserDto.Response> registerUser() {
        return success(
                null
        );
    }

    @PutMapping
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
