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
	
	@Autowired
	private EmailService emailService;
	
	
//	public Team createTeam(Team team) {
//		team.getTeamMembers().forEach(teamMember -> teamMember.setTeam(team));
//        return teamRepository.save(team);
//    }
	
//	public Team createTeam(Team team, Users user) {
//	    // Set the user for the team
//	    team.setUser(user);
//	    
//	    // Iterate through team members and set the team
//	    for (TeamMember member : team.getTeamMembers()) {
//	        member.setTeam(team);
//	    }
//	    
//	    return teamRepository.save(team);
//	}
	 public Team createTeam(Team team, Users user) {
	        // Set the user for the team
	        team.setUser(user);

	        // Iterate through team members and set the team
	        for (TeamMember member : team.getTeamMembers()) {
	            member.setTeam(team);
	        }

	        // Save the team to get the team ID
	        team = teamRepository.save(team);

	        // Get the details of all team members
	        List<String> teamMemberDetails = team.getTeamMembers().stream()
	                .map(member -> member.getName() + " - " + member.getEmail())
	                .collect(Collectors.toList());

	        // Send email to user who created the team
	        sendEmailToUser(user.getEmail(), team.getTeamName(), teamMemberDetails,
	                "Team creation successful", "Hello " + user.getUsername() + ",\n\nYour team '" + team.getTeamName()
	                        + "' has been created successfully. Here are the details of the team members:\n"
	                        + String.join("\n", teamMemberDetails));

	        // Iterate through team members and send email to each member
	        for (TeamMember member : team.getTeamMembers()) {
	            sendEmailToTeamMember(member.getName(), member.getEmail(), team.getTeamName(), teamMemberDetails,
	                    "You have been added to a team", "Hello " + member.getName() + ",\n\nYou have been added to the team '"
	                            + team.getTeamName() + "' for the hackathon. Here are the details of all team members:\n"
	                            + String.join("\n", teamMemberDetails));
	        }

	        return team;
	    }

	    private void sendEmailToUser(String userEmail, String teamName, List<String> teamMemberDetails, String subject,
	            String text) {
	        emailService.sendEmail(userEmail, subject, text);
	    }

	    private void sendEmailToTeamMember(String memberName, String email, String teamName, List<String> teamMemberDetails,
	            String subject, String text) {
	        emailService.sendEmail(email, subject, text);
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
    
    public Team convertToEntity(TeamDTO teamDTO) {
        Team team = new Team();
        team.setTeamName(teamDTO.getTeamName());
        team.setOwnerEmail(teamDTO.getOwnerEmail());
        
        // Convert TeamMemberDTOs to TeamMembers and add them to the team
        if (teamDTO.getTeamMembers() != null) {
            team.setTeamMembers(teamDTO.getTeamMembers().stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList()));
        }
        
        return team;
    }
    
    private TeamMember convertToEntity(TeamMemberDTO memberDTO) {
        TeamMember member = new TeamMember();
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        return member;
    }

}
