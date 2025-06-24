package org.project.ttokttok.domain.admin.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.ttokttok.domain.admin.controller.dto.request.AdminLoginRequest;
import org.project.ttokttok.domain.admin.controller.dto.response.AdminLoginResponse;
import org.project.ttokttok.domain.admin.service.auth.AdminAuthService;
import org.project.ttokttok.global.annotation.AuthUserInfo;
import org.project.ttokttok.global.jwt.TokenExpiry;
import org.project.ttokttok.global.jwt.TokenProperties;
import org.project.ttokttok.global.util.CookieUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

import static org.project.ttokttok.global.jwt.TokenExpiry.REFRESH_TOKEN_EXPIRY_TIME;
import static org.project.ttokttok.global.jwt.TokenProperties.AUTH_HEADER;
import static org.project.ttokttok.global.jwt.TokenProperties.REFRESH_KEY;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/auth")
public class AdminAuthApiController {

    private final AdminAuthService adminAuthService;
    private final CookieUtil cookieUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AdminLoginRequest request) {
        AdminLoginResponse response = AdminLoginResponse.from(adminAuthService.login(request.toServiceRequest()));
        ResponseCookie refreshCookie = CookieUtil.createResponseCookie(
                REFRESH_KEY.getValue(),
                response.refreshToken(),
                Duration.ofDays(REFRESH_TOKEN_EXPIRY_TIME.getExpiry())
        );

        return ResponseEntity.ok()
                .header(AUTH_HEADER.getValue(), response.accessToken())
                .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                .body("Admin Login Success");
    }

    public ResponseEntity<Void> logout(@AuthUserInfo String adminName) {
        adminAuthService.logout(adminName);
        // 쿠키 만료시키기
        ResponseCookie exiredResponseCookie = CookieUtil.exireResponseCookie(adminName);

        return ResponseEntity.noContent()
                .header(HttpHeaders.SET_COOKIE, exiredResponseCookie.toString())
                .build();
    }

    public ResponseEntity<Void> reissue() {
        return null;
    }

    // 관리자 계정 생성용 api, 프론트 측 구현 필요 X
    public ResponseEntity<Void> join() {
        return null;
    }
}