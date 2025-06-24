package org.project.ttokttok.global.jwt.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.project.ttokttok.global.entity.Role;
import org.project.ttokttok.global.jwt.dto.request.TokenRequest;
import org.project.ttokttok.global.jwt.dto.response.TokenResponse;
import org.project.ttokttok.global.jwt.dto.response.UserProfileResponse;
import org.project.ttokttok.global.jwt.exception.InvalidIssuerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import static org.project.ttokttok.global.jwt.TokenExpiry.ACCESS_TOKEN_EXPIRY_TIME;

@Slf4j
@Service
public class TokenProvider {

    @Value("${jwt.issuer}")
    private String issuer;

    private final Key key;

    public TokenProvider(@Value("${jwt.secret}") String secret) {
        // 시크릿값 base64 디코딩
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            isValidIssuer(jws.getBody().getIssuer());

            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.warn("잘못된 JWT 토큰 입니다.", e);
        } catch (ExpiredJwtException e) {
            log.warn("JWT 토큰이 만료되었습니다.", e);
        } catch (UnsupportedJwtException e) {
            log.warn("지원되지 않는 JWT 토큰", e);
        } catch (IllegalArgumentException e) {
            log.warn("JWT 토큰이 비어있습니다.", e);
        } catch (NullPointerException e) {
            log.warn("헤더 값이 잘못되었습니다.", e);
        }

        return false;
    }

    // 토큰 생성
    public TokenResponse generateToken(TokenRequest request) {
        String accessToken = generateAccessToken(request.username(), request.role());
        String refreshToken = generateRefreshToken();

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // 토큰에서 사용자 정보 추출
    public UserProfileResponse getUserProfile(String token) {
        return UserProfileResponse.builder()
                .username(getUsernameFromToken(token))
                .role(getRoleFromToken(token))
                .build();
    }

    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    private String getRoleFromToken(String token) {
        return getClaims(token).get("role", String.class);
    }

    private void isValidIssuer(String issuer) {
        // 이슈 발급자도 base64 인코딩되어 있기에 디코딩
        String decodedIssuer = Arrays.toString(Decoders.BASE64.decode(this.issuer));

        if (!issuer.equals(decodedIssuer))
            throw new InvalidIssuerException();
    }

    private String generateAccessToken(String username, Role role) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + ACCESS_TOKEN_EXPIRY_TIME.getExpiry());

        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .claim("role", role.toString())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private String generateRefreshToken() {
        return UUID.randomUUID().toString();
    }

    // 클레임 추출
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}