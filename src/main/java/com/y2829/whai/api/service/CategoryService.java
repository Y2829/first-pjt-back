package com.y2829.whai.api.service;

import com.y2829.whai.api.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAllCategory();

    List<Category> findAllCategoryByWord(String word);

}
