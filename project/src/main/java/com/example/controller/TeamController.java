package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.TeamDTO;
import com.example.DTO.TeamMemberDTO;
import com.example.entity.Team;
import com.example.entity.TeamMember;
import com.example.entity.Users;
import com.example.service.TeamService;
import com.example.service.user_services;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;
    
    @Autowired
    private user_services userService;
    
//    @PostMapping("/create")
//    public ResponseEntity<Object> createTeam(@RequestBody Team team) {
//        Team createdTeam = teamService.createTeam(team);
//        if (createdTeam != null) {
//            TeamDTO teamDTO = teamService.convertToDTO(createdTeam);
//            return new ResponseEntity<>(teamDTO, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Failed to create team", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @PostMapping("/create")
//    public ResponseEntity<Object> createTeam(@RequestBody TeamDTO teamDTO) {
//        // Convert TeamDTO to Team entity
//        Team team = teamService.convertToEntity(teamDTO);
//        
//        // Create the team
//        Team createdTeam = teamService.createTeam(team);
//        
//        if (createdTeam != null) {
//            // Convert created team back to DTO for response
//            TeamDTO createdTeamDTO = teamService.convertToDTO(createdTeam);
//            return new ResponseEntity<>(createdTeamDTO, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Failed to create team", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    @PostMapping("/create")
    public ResponseEntity<Object> createTeam(@RequestBody TeamDTO teamDTO, @RequestParam int id) {
        // Retrieve the user by ID
        Users user = userService.getUserById(id);
        
        if (user != null) {
            // Convert TeamDTO to Team entity
            Team team = teamService.convertToEntity(teamDTO);
            
            // Create the team
            Team createdTeam = teamService.createTeam(team, user);
            
            if (createdTeam != null) {
                // Convert created team back to DTO for response
                TeamDTO createdTeamDTO = teamService.convertToDTO(createdTeam);
                return new ResponseEntity<>(createdTeamDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to create team", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }    
    
    @GetMapping("/{teamId}")
    public ResponseEntity<?> getTeamDetails(@PathVariable int teamId) {
        Team team = teamService.getTeamById(teamId);
        if (team != null) {
            TeamDTO teamDTO = convertToDTO(team);
            return ResponseEntity.ok(teamDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public TeamDTO convertToDTO(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setTeamName(team.getTeamName());
        teamDTO.setOwnerEmail(team.getOwnerEmail());

        List<TeamMemberDTO> teamMemberDTOs = team.getTeamMembers().stream()
                .map(teamMember -> new TeamMemberDTO(teamMember.getName(), teamMember.getEmail()))
                .collect(Collectors.toList());
        teamDTO.setTeamMembers(teamMemberDTOs);

        return teamDTO;
    }
}
