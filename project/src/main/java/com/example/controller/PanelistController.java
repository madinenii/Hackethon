package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.IdeaDTO;
import com.example.entity.Idea;
import com.example.entity.IdeaStatus;
import com.example.service.IdeaService;
import com.example.service.PanelistService;

@RestController
@RequestMapping("/panelists")
public class PanelistController {

    @Autowired
    private PanelistService panelistService;
    
    @Autowired
    private IdeaService ideaService;

    // Endpoint to get submitted ideas
    @GetMapping("/submitted-ideas")
    public ResponseEntity<List<IdeaDTO>> getSubmittedIdeas() {
        List<IdeaDTO> submittedIdeas = panelistService.getAllSubmittedIdeas();
        return ResponseEntity.ok(submittedIdeas);
    }
    
    @PutMapping("/accept-idea/{ideaId}")
    public ResponseEntity<String> acceptIdea(@PathVariable int ideaId) {
        panelistService.acceptIdea(ideaId);
        return ResponseEntity.ok("Idea accepted successfully");
    }

    @PutMapping("/reject-idea/{ideaId}")
    public ResponseEntity<String> rejectIdea(@PathVariable int ideaId) {
        panelistService.rejectIdea(ideaId);
        return ResponseEntity.ok("Idea rejected successfully");
    }

}