package org.project.ttokttok.domain.admin.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.ttokttok.domain.admin.controller.dto.request.AdminLoginRequest;
import org.project.ttokttok.domain.admin.controller.dto.response.AdminLoginResponse;
import org.project.ttokttok.domain.admin.service.auth.AdminAuthService;
import org.project.ttokttok.global.util.CookieUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.project.ttokttok.global.jwt.TokenProperties.AUTH_HEADER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/admin")
public class AdminAuthApiController {

    private final AdminAuthService adminAuthService;
    private final CookieUtil cookieUtil;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid AdminLoginRequest request) {
        AdminLoginResponse response = AdminLoginResponse.from(adminAuthService.login(request.toServiceRequest()));
        ResponseCookie refreshCookie = CookieUtil.createRefreshTokenCookie(response.refreshToken());

        return ResponseEntity.ok()
                .header(AUTH_HEADER.getValue(), response.accessToken())
                .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                .build();
    }
}