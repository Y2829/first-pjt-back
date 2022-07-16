package com.y2829.whai.api.service.impl;

import com.y2829.whai.api.entity.Category;
import com.y2829.whai.api.repository.CategoryRepository;
import com.y2829.whai.api.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAllCategoryByWord(String word) {
        return categoryRepository.findBySubjectLike("%"+word+"%");
    }

}
