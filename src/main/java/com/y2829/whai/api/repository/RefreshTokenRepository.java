package com.y2829.whai.api.repository;

import com.y2829.whai.api.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByUserId(String userId);

    Optional<RefreshToken> findByUserIdAndToken(String userId, String token);

}
