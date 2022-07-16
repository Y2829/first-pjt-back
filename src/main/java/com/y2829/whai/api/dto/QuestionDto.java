package com.y2829.whai.api.dto;

import com.y2829.whai.api.entity.Question;
import com.y2829.whai.api.entity.Category;
import com.y2829.whai.api.entity.Image;
import com.y2829.whai.api.entity.QuestionCategory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionDto {

    @Getter
    @Setter
    public static class PostQuestionRequest {
        @NotNull
        private Long userId;
        @NotNull
        private String title;
        @NotNull
        private String content;
        private List<String> categories;
        private List<MultipartFile> images;

        public Question toEntity() {
            return Question.builder()
                    .title(title)
                    .content(content)
                    .status(Status.WAIT)
                    .createAt(LocalDateTime.now())
                    .build();
        }
    }

    @Getter
    @Setter
    public static class PatchQuestionRequest {
        @NotNull
        private Long userId;
        @NotNull
        private Long questionId;
        @NotNull
        private String title;
        @NotNull
        private String content;
        private List<String> categories;
        private List<MultipartFile> images;
    }

    @Getter
    @Setter
    public static class SimpleQuestion {
        private final Long questionId;
        private final Long userId;
        private final String title;
        private final String content;
        private final List<String> categories;
        private final List<String> imageUrls;

        public SimpleQuestion(Question question) {
            this.questionId = question.getId();
            this.userId = question.getUser().getId();
            this.title = question.getTitle();
            this.content = question.getContent();
            this.categories = question.getCategories().stream()
                    .map(QuestionCategory::getCategory)
                    .map(Category::getSubject)
                    .collect(Collectors.toList());
            this.imageUrls = question.getImages().stream()
                    .map(Image::getStoreFileName)
                    .collect(Collectors.toList());
        }
    }

    @Getter
    @Setter
    public static class PageQuestionResponse {
        private final Page<SimpleQuestion> questions;

        public PageQuestionResponse(Page<Question> questions) {
            List<SimpleQuestion> list = questions.stream()
                    .map(SimpleQuestion::new)
                    .collect(Collectors.toList());
            this.questions = new PageImpl<>(list);
        }
    }

}
