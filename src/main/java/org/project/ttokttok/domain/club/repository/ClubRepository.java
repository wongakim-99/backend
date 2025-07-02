package org.project.ttokttok.domain.club.repository;

import org.project.ttokttok.domain.club.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, String>, ClubCustomRepository{
}
