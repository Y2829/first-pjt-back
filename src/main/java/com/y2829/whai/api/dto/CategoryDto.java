package com.y2829.whai.api.dto;

import com.y2829.whai.api.entity.Category;

import java.util.List;

public class CategoryDto {

    public static class ListResponse {
        private final List<Category> categories;

        public ListResponse(List<Category> categories) {
            this.categories = categories;
        }
    }

}
