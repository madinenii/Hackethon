package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
}