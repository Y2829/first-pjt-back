package com.y2829.whai.api.dto;

import com.y2829.whai.api.entity.Question;
import com.y2829.whai.api.entity.Category;
import com.y2829.whai.api.entity.Image;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionDto {

    @Getter
    @Setter
    public static class PostRequest {
        private Long userId;
        private String title;
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
    public static class PutRequest {
        private Long userId;
        private Long questionId;
        private String title;
        private String content;
        private List<String> categories;
        private List<MultipartFile> images;
    }

    @Getter
    @Setter
    public static class SimpleQuestion {
        private final Long userId;
        private final String title;
        private final String content;
        private final List<String> categories;
        private final List<String> imageUrls;

        public SimpleQuestion(Question question) {
            this.userId = question.getUser().getId();
            this.title = question.getTitle();
            this.content = question.getContent();
            this.categories = question.getCategories().stream()
                    .map(Category::getSubject)
                    .collect(Collectors.toList());
            this.imageUrls = question.getImages().stream()
                    .map(Image::getStoreFileName)
                    .collect(Collectors.toList());
        }
    }

    @Getter
    @Setter
    public static class PageResponse {
        private Page<SimpleQuestion> questions;

        public PageResponse(Page<Question> questions) {
            List<SimpleQuestion> list = questions.stream()
                    .map(SimpleQuestion::new)
                    .collect(Collectors.toList());
            this.questions = new PageImpl<>(list);
        }
    }

}
