package com.y2829.whai.api.controller;

import com.y2829.whai.api.service.UserService;
import com.y2829.whai.common.utils.ApiUtils.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.y2829.whai.api.dto.UserDto.*;
import static com.y2829.whai.common.utils.ApiUtils.success;

@Tag(name = "User API", description = "유저 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserRestController {

    private final UserService userService;

    @PatchMapping
    @Operation(summary = "회사 정보 삽입", description = "회사 정보를 삽입합니다.", security = { @SecurityRequirement(name = "bearer-key")}, deprecated = true)
    public ApiResult<Long> patchUser(
            @Valid @RequestBody PatchUserRequest request
    ) {
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return success(
                userService.modifyUser(request, principal.getUsername())
        );
    }

    @DeleteMapping("{userId}")
    @Operation(summary = "회원 탈퇴", description = "회원을 탈퇴합니다.", security = { @SecurityRequirement(name = "bearer-key")})
    public ApiResult<Long> deleteUser(
            @PathVariable Long userId
    ) {
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return success(
                userService.removeUser(userId, principal.getUsername())
        );
    }

    @GetMapping
    @Operation(summary = "회원 조회", description = "회원을 조회합니다.", security = { @SecurityRequirement(name = "bearer-key")})
    public ApiResult<SimpleUser> getUser() {
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return success(
                new SimpleUser(userService.findUser(principal.getUsername()))
        );
    }

}
