package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamMemberService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void updateTeamMemberTeamIds() {
        String sql = "UPDATE team_member tm " +
                     "JOIN team t ON tm.team_id = t.id " +
                     "SET tm.team_id = t.id";

        jdbcTemplate.update(sql);
    }
}
