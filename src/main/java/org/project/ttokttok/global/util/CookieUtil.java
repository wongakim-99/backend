package org.project.ttokttok.global.util;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class CookieUtil {

    private static final String ROOT = "/";

    public static ResponseCookie createResponseCookie(String key, String content, Duration expiry) {
        return ResponseCookie.from(key, content)
                .httpOnly(true)
                .secure(true)
                .path(ROOT)
                .maxAge(expiry)
                .build();
    }

    public static ResponseCookie exireResponseCookie(String key) {
        return createResponseCookie(key, "", Duration.ZERO);
    }
}
