package com.y2829.whai.api.dto;

import com.y2829.whai.api.entity.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CategoryDto {

    @Getter
    @Setter
    public static class ListCategoryResponse {
        private final List<Category> categories;

        public ListCategoryResponse(List<Category> categories) {
            this.categories = categories;
        }
    }

}
