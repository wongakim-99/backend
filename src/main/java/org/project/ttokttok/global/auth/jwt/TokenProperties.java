package org.project.ttokttok.global.auth.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenProperties {
    //토큰 관련 상수
    AUTH_HEADER("Authorization"),
    REFRESH_KEY("ttref"),
    ACCESS_TOKEN_COOKIE("ttac"), // 액세스 토큰 쿠키 이름 추가
    
    // 사용자용 쿠키 이름 (관리자와 구분하기 위해)
    USER_REFRESH_KEY("ttref_user"),
    USER_ACCESS_TOKEN_COOKIE("ttac_user");

    final String value;
}
