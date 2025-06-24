package org.project.ttokttok.global.jwt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.ttokttok.global.jwt.exception.AlreadyLogoutException;
import org.project.ttokttok.global.jwt.exception.RefreshTokenAlreadyExistsException;
import org.project.ttokttok.global.jwt.exception.RefreshTokenExpiredException;
import org.project.ttokttok.global.jwt.exception.RefreshTokenNotFoundException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.project.ttokttok.global.jwt.TokenExpiry.REFRESH_TOKEN_EXPIRY_TIME;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenRedisService {

    private final RedisTemplate<String, String> redisTemplate;
    private static final String REFRESH_REDIS_KEY = "refresh:";

    public void save(String username, String refreshToken) {
        if (isExistKey(username)) { // 리프레시 토큰이 존재하면 예외 발생
            throw new RefreshTokenAlreadyExistsException();
        }

        redisTemplate.opsForValue().set(
                REFRESH_REDIS_KEY + username,
                refreshToken,
                Duration.ofMillis(REFRESH_TOKEN_EXPIRY_TIME.getExpiry()));
    }

    public String getRefreshToken(String username) {
        if (isExistKey(username)) {
            return redisTemplate.opsForValue().get(REFRESH_REDIS_KEY + username);
        }

        throw new RefreshTokenNotFoundException();
    }

    public void deleteRefreshToken(String username) {
        // 추후 필요하다면, 액세스토큰 블랙리스트 로직 추가 고려하기
        if (isExistKey(username)) {
            log.info("로그아웃 완료: {}, logout at: {}", username, LocalDateTime.now());
            redisTemplate.delete(REFRESH_REDIS_KEY + username);
        }

        throw new AlreadyLogoutException();
    }

    public void updateRefreshToken(String username, String refreshToken) {
        // 토큰 남은 시간
        Long refreshTTL = redisTemplate.getExpire(REFRESH_REDIS_KEY + username, TimeUnit.MILLISECONDS);

        tokenAliveValidate(refreshTTL);

        // 새로운 리프레시 토큰으로 다시 내려줌.
        redisTemplate.opsForValue().set(REFRESH_REDIS_KEY + username, refreshToken, refreshTTL);
    }

    private void tokenAliveValidate(Long refreshTTL) {
        if (refreshTTL == null) {
            throw new RefreshTokenExpiredException();
        }
    }

    private boolean isExistKey(String username) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(REFRESH_REDIS_KEY + username));
    }
}
