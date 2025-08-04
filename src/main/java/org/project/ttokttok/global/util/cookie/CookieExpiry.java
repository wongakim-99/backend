package org.project.ttokttok.global.util.cookie;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CookieExpiry {

    // 우선, 리프레시 토큰 만료 시간과 동일하게 설정.
    TOKEN_COOKIE_EXPIRY_TIME(7 * 24 * 60 * 60 * 1000L); // 7일

    final Long expiry;
}
