package com.y2829.whai.api.controller;

import com.y2829.whai.api.service.MentorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.y2829.whai.api.dto.MentorDto.*;
import static com.y2829.whai.common.utils.ApiUtils.ApiResult;
import static com.y2829.whai.common.utils.ApiUtils.success;

@Tag(name = "Mentor API", description = "멘토 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/mentor")
public class MentorRestController {

    private final MentorService mentorService;

    @PostMapping
    @Operation(summary = "멘토 등록", description = "멘토를 등록합니다.", security = { @SecurityRequirement(name = "bearer-key") })
    public ApiResult<Long> createMentor(@RequestBody PostMentorRequest requestMentor) {
        return success(
            mentorService.saveMentor(requestMentor)
        );
    }

    @GetMapping("/list")
    @Operation(summary = "전체 멘토 조회", description = "모든 멘토를 조회합니다.")
    public ApiResult<MentorResponse> getMentors(Pageable pageable) {
        return success(
                new MentorResponse(mentorService.findAll(pageable))
        );
    }

    @PatchMapping
    @Operation(summary = "멘토 수정", description = "멘토를 수정합니다.", security = { @SecurityRequirement(name = "bearer-key") })
    public ApiResult<Long> modifyMentor(@Valid @RequestBody PatchMentorRequest request) {
        return success(
                mentorService.modifyMentor(request)
        );
    }

    @DeleteMapping("{userId}/{mentorId}")
    @Operation(summary = "멘토 삭제", description = "멘토를 삭제합니다.", security = { @SecurityRequirement(name = "bearer-key") })
    public ApiResult<Long> removeMentor(@PathVariable Long userId, @PathVariable Long mentorId) {
        return success(
                mentorService.removeMentor(mentorId)
        );
    }

}
