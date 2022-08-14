package com.y2829.whai.api.dto;

import com.y2829.whai.api.entity.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDto {

    @Getter
    @Setter
    public static class SimpleCategory {
        private final Long categoryId;
        private final String subject;

        public SimpleCategory(Category category) {
            this.categoryId = category.getId();
            this.subject = category.getSubject();
        }
    }

    @Getter
    @Setter
    public static class ListCategoryResponse {
        private final List<SimpleCategory> categories;

        public ListCategoryResponse(List<Category> categories) {
            this.categories = categories.stream()
                    .map(SimpleCategory::new)
                    .collect(Collectors.toList());
        }
    }

}
