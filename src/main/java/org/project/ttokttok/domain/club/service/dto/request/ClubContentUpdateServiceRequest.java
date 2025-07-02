package org.project.ttokttok.domain.club.service.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.openapitools.jackson.nullable.JsonNullable;
import org.project.ttokttok.domain.club.domain.enums.ClubCategory;
import org.project.ttokttok.domain.club.domain.enums.ClubType;
import org.springframework.core.io.Resource;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClubContentUpdateServiceRequest(
        String clubId,
        JsonNullable<String> name,
        JsonNullable<ClubType> clubType,
        JsonNullable<ClubCategory> clubCategory,
        JsonNullable<String> customCategory,
        JsonNullable<String> summary,
        JsonNullable<Resource> profileImage,
        JsonNullable<String> content,
        JsonNullable<Boolean> recruiting
) {
}