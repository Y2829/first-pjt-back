package com.y2829.whai.api.service;

import com.y2829.whai.api.entity.Mentor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.y2829.whai.api.dto.MentorDto.PatchMentorRequest;
import static com.y2829.whai.api.dto.MentorDto.PostMentorRequest;

public interface MentorService {

    Long saveMentor(PostMentorRequest requestMentor);

    Long modifyMentor(PatchMentorRequest request);

    Page<Mentor> findAll(Pageable pageable);

    Long removeMentor(Long mentorId);
}
