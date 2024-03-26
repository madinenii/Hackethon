package com.example.controller;

import java.util.List;

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

import com.example.DTO.IdeaDTO;
import com.example.entity.Idea;
import com.example.entity.IdeaStatus;
import com.example.entity.Team;
import com.example.entity.Users;
import com.example.service.IdeaService;
import com.example.service.TeamService;
import com.example.service.user_services;

@RestController
@RequestMapping("/ideas")
public class IdeaController {

    @Autowired
    private IdeaService ideaService;
    
    @Autowired
    private user_services services;
    
    @Autowired
    private TeamService teamService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitIdea(@RequestBody Idea idea, @RequestParam int id, @RequestParam int teamId) {
    	Users user = services.getUserById(id);
    	Team team = teamService.getTeamById(teamId);
    	 if (user == null || team == null) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or team not found");
         }
        boolean submissionSuccess = ideaService.submitIdea(idea, user, team);
        if (submissionSuccess) {
            return ResponseEntity.ok("Idea submitted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to submit idea");
        }
    }
    @GetMapping("/user/{userId}/ideas")
    public ResponseEntity<List<IdeaDTO>> getIdeasByUserId(@PathVariable int userId) {
        List<IdeaDTO> ideas = ideaService.getIdeasByUserId(userId);
        return ResponseEntity.ok(ideas);
    }
//    
//    @GetMapping("/ranked-ideas")
//    public ResponseEntity<List<Idea>> getRankedIdeas() {
//        try {
//            List<Idea> rankedIdeas = ideaService.getRankedIdeas();
//            return ResponseEntity.ok(rankedIdeas);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null);
//        }
//    }
//    
    @GetMapping("/{ideaId}/status")
    public ResponseEntity<String> getIdeaStatus(@PathVariable Long ideaId) {
        Idea idea = ideaService.getIdeaById(ideaId);
        if (idea != null) {
            IdeaStatus status = idea.getStatus();
            return ResponseEntity.ok(status.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Idea not found with ID: " + ideaId);
        }
    }
    @GetMapping("/ranked-ideas")
    public ResponseEntity<List<IdeaDTO>> getRankedIdeas() {
        try {
            List<IdeaDTO> rankedIdeas = ideaService.getRankedIdeas();
            return ResponseEntity.ok(rankedIdeas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}