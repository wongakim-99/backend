package org.project.ttokttok.domain.admin.service.auth;

import lombok.RequiredArgsConstructor;
import org.project.ttokttok.domain.admin.domain.Admin;
import org.project.ttokttok.domain.admin.exception.AdminNotFoundException;
import org.project.ttokttok.domain.admin.repository.AdminRepository;
import org.project.ttokttok.domain.admin.service.dto.request.AdminLoginServiceRequest;
import org.project.ttokttok.domain.admin.service.dto.response.AdminLoginServiceResponse;
import org.project.ttokttok.global.entity.Role;
import org.project.ttokttok.global.jwt.dto.request.TokenRequest;
import org.project.ttokttok.global.jwt.dto.response.TokenResponse;
import org.project.ttokttok.global.jwt.service.RefreshTokenRedisService;
import org.project.ttokttok.global.jwt.service.TokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.project.ttokttok.global.entity.Role.ROLE_ADMIN;

@Service
@RequiredArgsConstructor
public class AdminAuthService {

    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRedisService refreshTokenRedisService;

    public AdminLoginServiceResponse login(AdminLoginServiceRequest request) {
        Admin targetAdmin = adminRepository.findByUsername(request.username())
                .orElseThrow(AdminNotFoundException::new);

        targetAdmin.validatePassword(request.password(), passwordEncoder);

        TokenResponse tokenResponse = getTokenResponse(targetAdmin.getUsername());

        return AdminLoginServiceResponse.from(tokenResponse);
    }

    public void logout(String username) {
        refreshTokenRedisService.deleteRefreshToken(username);
    }

    private TokenResponse getTokenResponse(String username) {
        TokenResponse tokenResponse = tokenProvider.generateToken(TokenRequest.of(username, ROLE_ADMIN));
        refreshTokenRedisService.save(username, tokenResponse.refreshToken());

        return tokenResponse;
    }
}
