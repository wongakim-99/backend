package org.project.ttokttok.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${spring.profiles.active:local}")
    private String activeProfile;

    @Bean
    public OpenAPI boardAPI() {
        Info info = createSwaggerInfo();

        // 관리자용 쿠키 기반 인증 설정
        SecurityScheme adminCookieAuth = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.COOKIE)
                .name("ttac") // 관리자 Access Token 쿠키
                .description("관리자 로그인 후 자동으로 설정되는 액세스 토큰 쿠키");

        // 관리자용 리프레시 토큰 쿠키 설정
        SecurityScheme adminRefreshCookieAuth = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.COOKIE)
                .name("ttref") // 관리자 Refresh Token 쿠키
                .description("관리자 로그인 후 자동으로 설정되는 리프레시 토큰 쿠키");

        // 사용자용 쿠키 기반 인증 설정
        SecurityScheme userCookieAuth = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.COOKIE)
                .name("ttac_user") // 사용자 Access Token 쿠키
                .description("사용자 로그인 후 자동으로 설정되는 액세스 토큰 쿠키");

        // 사용자용 리프레시 토큰 쿠키 설정
        SecurityScheme userRefreshCookieAuth = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.COOKIE)
                .name("ttref_user") // 사용자 Refresh Token 쿠키
                .description("사용자 로그인 후 자동으로 설정되는 리프레시 토큰 쿠키");

        // 보안 요구사항 설정
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("adminCookieAuth")
                .addList("adminRefreshCookieAuth")
                .addList("userCookieAuth")
                .addList("userRefreshCookieAuth");

        // 환경별 서버 설정
        List<Server> servers = createServersByEnvironment();

        return new OpenAPI()
                .info(info)
                .servers(servers)
                .components(new Components()
                        .addSecuritySchemes("adminCookieAuth", adminCookieAuth)
                        .addSecuritySchemes("adminRefreshCookieAuth", adminRefreshCookieAuth)
                        .addSecuritySchemes("userCookieAuth", userCookieAuth)
                        .addSecuritySchemes("userRefreshCookieAuth", userRefreshCookieAuth))
                .security(Collections.singletonList(securityRequirement));
    }

    private List<Server> createServersByEnvironment() {
        if ("prod".equals(activeProfile)) {
            // 프로덕션 환경: 프로덕션 서버를 첫 번째로 설정
            return List.of(
                new Server()
                    .url("https://www.hearmeout.kr")
                    .description("Production Server (기본값)"),
                new Server()
                    .url("http://localhost:8080")
                    .description("Local Development Server")
            );
        } else {
            // 개발 환경: 로컬 서버를 첫 번째로 설정
            return List.of(
                new Server()
                    .url("http://localhost:8080")
                    .description("Local Development Server (기본값)"),
                new Server()
                    .url("https://www.hearmeout.kr")
                    .description("Production Server")
            );
        }
    }

    private Info createSwaggerInfo() {
        return new Info()
                .title("똑똑 게시판 API")
                .description("""
                        똑똑 API 문서입니다.
                        
                        ## 현재 환경: %s
                        
                        ## 인증 방법
                        1. 로그인 API를 호출합니다 (관리자: `/api/admin/auth/login`, 사용자: `/api/user/auth/login`)
                        2. 응답으로 자동으로 쿠키가 설정됩니다:
                           - 관리자: `ttac` (액세스 토큰), `ttref` (리프레시 토큰)
                           - 사용자: `ttac_user` (액세스 토큰), `ttref_user` (리프레시 토큰)
                        3. 이후 API 호출 시 자동으로 쿠키가 전송됩니다
                        
                        **참고**: 쿠키는 httpOnly로 설정되어 있어 JavaScript로 직접 접근할 수 없습니다.
                        
                        ## 서버 전환
                        - 개발 환경: Local Development Server 사용 권장
                        - 프로덕션 환경: Production Server 사용 권장
                        """.formatted(activeProfile))
                .version("0.0.1");
    }
}