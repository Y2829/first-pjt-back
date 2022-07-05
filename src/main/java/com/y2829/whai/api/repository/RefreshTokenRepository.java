package com.y2829.whai.api.repository;

import com.y2829.whai.api.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    RefreshToken findByUserId(String userId);

}
