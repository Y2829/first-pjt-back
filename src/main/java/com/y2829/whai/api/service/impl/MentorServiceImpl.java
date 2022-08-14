package com.y2829.whai.api.service.impl;

import com.y2829.whai.api.entity.Mentor;
import com.y2829.whai.api.entity.User;
import com.y2829.whai.api.repository.MentorRepository;
import com.y2829.whai.api.repository.UserRepository;
import com.y2829.whai.api.service.MentorService;
import com.y2829.whai.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.y2829.whai.api.dto.MentorDto.PatchMentorRequest;
import static com.y2829.whai.api.dto.MentorDto.PostMentorRequest;

@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {

    private static final String NOT_FOUND_USER = "Not Found User!!";
    private static final String NOT_FOUND_MENTOR = "Not Found Mentor!!";

    private final MentorRepository mentorRepository;
    private final UserRepository userRepository;
    @Override
    public Long saveMentor(PostMentorRequest requestMentor) {
        Mentor mentor = requestMentor.toEntity();

        User user = userRepository.findById(requestMentor.getUserId())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_USER));
        mentor.setUser(user);

        return mentorRepository.save(mentor).getId();
    }

    @Override
    public Long modifyMentor(PatchMentorRequest request) {
        Mentor mentor = mentorRepository.findById(request.getMentorId())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MENTOR));

        mentor.update(request);

        return mentorRepository.save(mentor).getId();
    }

    @Override
    public Page<Mentor> findAll(Pageable pageable) {
        return mentorRepository.findAll(pageable);
    }

    @Override
    public Long removeMentor(Long mentorId) {

        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new NotFoundException("NOT_FOUND_MENTOR"));

        mentorRepository.delete(mentor);

        return mentor.getId();
    }
}
