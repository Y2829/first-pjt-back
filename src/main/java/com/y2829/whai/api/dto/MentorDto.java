package com.y2829.whai.api.dto;

import com.y2829.whai.api.entity.Mentor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MentorDto {

    @Setter
    @Getter
    public static class PostMentorRequest {
        @NotNull
        private Long userId;
        @NotNull
        private String company;
        @NotNull
        private String description;

        public Mentor toEntity() {
            return Mentor.builder()
                    .company(company)
                    .description(description)
                    .answerTime(LocalDateTime.now())
                    .build();
        }
    }

    @Getter
    @Setter
    public static class PatchMentorRequest {
        @NotNull
        private Long mentorId;
        @NotNull
        private Long userId;
        @NotNull
        private String  company;
        @NotNull
        private String description;
    }

    @Getter
    @Setter
    public static class SimpleMentor {
        private Long userId;
        private String  company;
        private String description;

        public SimpleMentor(Mentor mentor) {
            this.userId = mentor.getUser().getId();
            this.company = mentor.getCompany();
            this.description = mentor.getCompany();

        }
    }

    @Getter
    @Setter
    public static class MentorResponse {
        private Page<SimpleMentor> mentors;
        public MentorResponse(Page<Mentor> Mentors) {
            List<SimpleMentor> allMentor = Mentors.stream()
                    .map(SimpleMentor::new)
                    .collect(Collectors.toList());

            this.mentors = new PageImpl<>(allMentor);
        }
    }

}
