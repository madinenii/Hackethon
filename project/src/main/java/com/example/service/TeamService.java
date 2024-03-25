package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.TeamDTO;
import com.example.DTO.TeamMemberDTO;
import com.example.entity.Team;
import com.example.entity.TeamMember;
import com.example.entity.Users;
import com.example.repo.TeamRepository;
import com.example.repo.user_repo;

@Service
public class TeamService {
	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private user_repo userRepository;
	
	
	public Team createTeam(Team team) {
        return teamRepository.save(team);
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
	

    public Team getTeamById(int teamId) {
        return teamRepository.findById(teamId).orElse(null);
    }
    
}
