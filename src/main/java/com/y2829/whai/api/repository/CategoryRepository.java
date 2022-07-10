package com.y2829.whai.api.repository;

import com.y2829.whai.api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllBySubjectIn(List<String> subjects);

}
