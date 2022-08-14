package com.y2829.whai.api.repository;

import com.y2829.whai.api.entity.Mentor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {

    Page<Mentor> findAll(Pageable pageable);

}
