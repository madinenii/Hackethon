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
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.IdeaDTO;
import com.example.entity.Idea;
import com.example.service.IdeaService;

@RestController
@RequestMapping("/ideas")
public class IdeaController {

    @Autowired
    private IdeaService ideaService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitIdea(@RequestBody Idea idea) {
        boolean submissionSuccess = ideaService.submitIdea(idea);
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