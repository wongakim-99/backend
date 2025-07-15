package org.project.ttokttok.domain.favorite.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.ttokttok.domain.favorite.controller.dto.response.FavoriteListResponse;
import org.project.ttokttok.domain.favorite.controller.dto.response.FavoriteToggleResponse;
import org.project.ttokttok.domain.favorite.service.FavoriteService;
import org.project.ttokttok.domain.favorite.service.dto.request.FavoriteToggleServiceRequest;
import org.project.ttokttok.global.annotation.auth.AuthUserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 즐겨찾기 관련 API 컨트롤러
 * 사용자의 즐겨찾기 추가/제거 및 조회 기능을 제공합니다.
 */
@Slf4j
@Tag(name = "즐겨찾기", description = "동아리 즐겨찾기 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    /**
     * 즐겨찾기 토글 API
     * 동아리를 즐겨찾기에 추가하거나 제거합니다.
     *
     * @param userEmail 인증된 사용자 이메일
     * @param clubId 동아리 ID
     * @return 즐겨찾기 토글 결과
     */
    @Operation(
            summary = "즐겨찾기 토글",
            description = "동아리를 즐겨찾기에 추가하거나 제거합니다. 이미 즐겨찾기가 되어 있으면 제거하고, 없으면 추가합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "즐겨찾기 토글 성공"),
            @ApiResponse(responseCode = "404", description = "동아리를 찾을 수 없음"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자")
    })
    @PostMapping("/toggle/{clubId}")
    public ResponseEntity<FavoriteToggleResponse> toggleFavorite(
            @Parameter(description = "JWT 토큰에서 추출한 사용자 이메일", required = false)
            @AuthUserInfo String tokenUserEmail,
            @Parameter(description = "동아리 ID", required = true)
            @PathVariable String clubId,
            @Parameter(description = "테스트용 사용자 이메일", required = false)
            @RequestParam(required = false) String userEmail) {

        // 테스트용: 쿼리 파라미터가 있으면 사용, 없으면 JWT에서 추출
        String actualUserEmail = userEmail != null ? userEmail : tokenUserEmail;

        if (actualUserEmail == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        FavoriteToggleServiceRequest request = FavoriteToggleServiceRequest.of(actualUserEmail, clubId);
        FavoriteToggleResponse response = FavoriteToggleResponse.from(
                favoriteService.toggleFavorite(request)
        );

        return ResponseEntity.ok(response);
    }

    /**
     * 즐겨찾기 목록 조회 API
     * 사용자의 즐겨찾기한 동아리 목록을 조회합니다.
     *
     * @param userEmail 인증된 사용자 이메일
     * @return 즐겨찾기 동아리 목록
     */
    @Operation(
            summary = "즐겨찾기 목록 조회",
            description = "사용자의 즐겨찾기한 동아리 목록을 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "즐겨찾기 목록 조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자")
    })
    @GetMapping
    public ResponseEntity<FavoriteListResponse> getFavoriteList(
            @AuthUserInfo String tokenUserEmail,
            @Parameter(description = "테스트용 사용자 이메일", required = false)
            @RequestParam(required = false) String userEmail) {

        // 테스트용: 쿼리 파라미터가 있으면 사용, 없으면 JWT에서 추출
        String actualUserEmail = userEmail != null ? userEmail : tokenUserEmail;

        if (actualUserEmail == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        FavoriteListResponse response = FavoriteListResponse.from(
                favoriteService.getFavoriteList(actualUserEmail)
        );

        return ResponseEntity.ok(response);
    }

    /**
     * 즐겨찾기 상태 확인 API
     * 특정 동아리의 즐겨찾기 상태를 확인합니다.
     *
     * @param userEmail 인증된 사용자 이메일
     * @param clubId 동아리 ID
     * @return 즐겨찾기 여부
     */
    @Operation(
            summary = "즐겨찾기 상태 확인",
            description = "특정 동아리가 즐겨찾기되어 있는지 확인합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "즐겨찾기 상태 확인 성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자")
    })
    @GetMapping("/status/{clubId}")
    public ResponseEntity<Boolean> getFavoriteStatus(
            @AuthUserInfo String tokenUserEmail,
            @Parameter(description = "동아리 ID", required = true)
            @PathVariable String clubId,
            @Parameter(description = "테스트용 사용자 이메일", required = false)
            @RequestParam(required = false) String userEmail) {

        // 테스트용: 쿼리 파라미터가 있으면 사용, 없으면 JWT에서 추출
        String actualUserEmail = userEmail != null ? userEmail : tokenUserEmail;

        if (actualUserEmail == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        boolean isFavorited = favoriteService.isFavorited(actualUserEmail, clubId);

        return ResponseEntity.ok(isFavorited);
    }
} 