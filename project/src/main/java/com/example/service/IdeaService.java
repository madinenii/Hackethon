package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.IdeaDTO;
import com.example.entity.Idea;
import com.example.entity.IdeaStatus;
import com.example.entity.RanksEnum;
import com.example.entity.Team;
import com.example.entity.TeamMember;
import com.example.entity.Users;
import com.example.exception.IdeaNotFoundException;
import com.example.repo.IdeaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class IdeaService {

    @Autowired
    private IdeaRepository ideaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private TeamService teamService;

//    public boolean submitIdea(Idea idea,Users user,Team team) {
//        try {
//        	idea.setTeam(team);
//        	idea.setUser(user);
//            idea.setStatus(IdeaStatus.SUBMITTED); // Set status to SUBMITTED
//            ideaRepository.save(idea); 
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
    public boolean submitIdea(Idea idea, Users user, Team team) {
        try {
            idea.setTeam(team);
            idea.setUser(user);
            idea.setStatus(IdeaStatus.SUBMITTED); // Set status to SUBMITTED
            ideaRepository.save(idea);

            // Send email notification to all team members
            sendIdeaSubmissionNotificationToTeamMembers(idea, team);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void sendIdeaSubmissionNotificationToTeamMembers(Idea idea, Team team) {
        // Compose email content
        String emailSubject = "Idea Submitted Successfully";
        String emailContent = "Hello Team Members,\n\n"
                + "A new idea has been submitted by a team member. Here are the details:\n\n"
                + "Idea Title: " + idea.getIdeaTitle() + "\n"
                + "Idea Description: " + idea.getIdeaDescription() + "\n"
                + "Idea Domain: " + idea.getIdeaDomain() + "\n"
                + "Submission URL: " + idea.getSubmissionUrl() + "\n\n"
                + "Team Name: " + team.getTeamName() + "\n\n"
                + "Team Members:\n";

        // Add team members to the email content
        for (TeamMember member : team.getTeamMembers()) {
            emailContent += " - " + member.getName() + " (" + member.getEmail() + ")\n";
        }

        emailContent += "\nThank you for your submission.";

        // Send email to all team members
        for (TeamMember member : team.getTeamMembers()) {
            emailService.sendEmail(member.getEmail(), emailSubject, emailContent);
        }
    }
     // Assuming you have ModelMapper configured
    public Idea getIdeaById(Long ideaId) {
        Optional<Idea> optionalIdea = ideaRepository.findById(ideaId);
        return optionalIdea.orElse(null);
    }

    public List<IdeaDTO> getIdeasByUserId(int userId) {
        List<Idea> ideas = ideaRepository.findByUserId(userId);
        return ideas.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
    }

    private IdeaDTO convertToDto(Idea idea) {
        return modelMapper.map(idea, IdeaDTO.class);
    }
    
    public void updateIdeaStatus(Long ideaId, IdeaStatus status) {
        Optional<Idea> optionalIdea = ideaRepository.findById(ideaId);
        if (optionalIdea.isPresent()) {
            Idea idea = optionalIdea.get();
            idea.setStatus(status);
            ideaRepository.save(idea);
        } else {
            // Handle case where idea with given ID is not found
            throw new IdeaNotFoundException("Idea not found with ID: " + ideaId);
        }
    }
    
//    public List<Idea> getRankedIdeas() {
//        List<Idea> allRankedIdeas = ideaRepository.findAllByOrderByTotalMarksDesc();
//        return allRankedIdeas.stream().limit(3).collect(Collectors.toList());
//    }
//    public List<IdeaDTO> getRankedIdeas() {
//        List<Idea> allRankedIdeas = ideaRepository.findAllByOrderByTotalMarksDesc();
//        return allRankedIdeas.stream()
//                .limit(3)
//                .map(this::convertToIdeaDTO)
//                .collect(Collectors.toList());
//    }
    public List<IdeaDTO> getRankedIdeas() {
        // Fetch the top 3 ranked ideas
        List<Idea> allRankedIdeas = ideaRepository.findAllByOrderByTotalMarksDesc().stream().limit(3).collect(Collectors.toList());

        // Convert each Idea entity to IdeaDTO
        return allRankedIdeas.stream()
                .map(idea -> {
                    // Fetch the team name for the current idea
                    String teamName = idea.getTeam().getTeamName(); // Assuming there is a getTeam() method in Idea entity

                    // Convert the Idea entity to IdeaDTO
                    IdeaDTO ideaDTO = convertToIdeaDTO(idea);

                    // Set the team name in IdeaDTO
                    ideaDTO.setTeamName(teamName);

                    return ideaDTO;
                })
                .collect(Collectors.toList());
    }

    private IdeaDTO convertToIdeaDTO(Idea idea) {
        IdeaDTO ideaDTO = new IdeaDTO();
        ideaDTO.setIdeaId(idea.getIdeaId());
        ideaDTO.setIdeaTitle(idea.getIdeaTitle());
        ideaDTO.setIdeaDescription(idea.getIdeaDescription());
        ideaDTO.setIdeaDomain(idea.getIdeaDomain());
        ideaDTO.setRanking(idea.getRanking());
        return ideaDTO;
    }


}