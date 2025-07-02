package org.project.ttokttok.domain.club.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project.ttokttok.domain.admin.domain.Admin;
import org.project.ttokttok.domain.club.domain.enums.ClubCategory;
import org.project.ttokttok.domain.club.domain.enums.ClubType;
import org.project.ttokttok.global.entity.BaseTimeEntity;

import java.util.UUID;

@Entity
@Getter
@Table(name = "clubs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Club extends BaseTimeEntity {

    //UUID 생성 전략
    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    @Id
    @Column(length = 36, updatable = false, unique = true)
    private String id;

    @Setter
    @Column(nullable = false, unique = true)
    private String name;

    @Setter
    @Column(name = "profile_img")
    private String profileImageUrl;

    @Setter
    private String summary; // 한줄 소개

    @Setter
    @Enumerated(EnumType.STRING)
    private ClubType clubType; // 동아리 유형

    @Setter
    @Enumerated(EnumType.STRING)
    private ClubCategory clubCategory; // 동아리 유형

    @Setter
    @Column(length = 30)
    private String customCategory;

    @Setter
    @Column(columnDefinition = "TEXT")
    private String content;

    @Setter
    private boolean recruiting; // 모집여부

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

}
