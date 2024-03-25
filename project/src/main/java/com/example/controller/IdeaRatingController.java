package com.example.controller;


import com.example.DTO.IdeaRatingDTO;
import com.example.DTO.IdeaRatingRequest;
import com.example.service.IdeaRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IdeaRatingController {

    private final IdeaRatingService ideaRatingService;

    @Autowired
    public IdeaRatingController(IdeaRatingService ideaRatingService) {
        this.ideaRatingService = ideaRatingService;
    }

//    @PutMapping("/ideas/rate")
//    public ResponseEntity<String> rateIdea(@RequestBody IdeaRatingDTO ideaRatingDTO) {
//        ideaRatingService.rateIdea(ideaRatingDTO.getIdeaId(), 
//                                   ideaRatingDTO.getWorkflowMarks(),
//                                   ideaRatingDTO.getExplanationMarks(),
//                                   ideaRatingDTO.getImplementationMarks(),
//                                   ideaRatingDTO.getPresentationMarks());
//        return ResponseEntity.ok("Idea rated successfully!");
//    }
    @PutMapping("/ideas/{ideaId}/rate")
    public ResponseEntity<String> rateIdea(@PathVariable Long ideaId, @RequestBody IdeaRatingRequest request) {
        try {
            ideaRatingService.rateIdea(ideaId, request.getWorkflowMarks(), request.getExplanationMarks(),
                    request.getImplementationMarks(), request.getPresentationMarks());
            return ResponseEntity.ok("Idea rated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to rate idea: " + e.getMessage());
        }
    }
    @PostMapping("/calculate-ranks")
    public ResponseEntity<String> calculateRanks() {
        try {
        	ideaRatingService.calculateRanksForAcceptedIdeas();
            return ResponseEntity.ok("Ranks calculated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to calculate ranks: " + e.getMessage());
        }
    }
    @PostMapping("/update-ranks")
    public ResponseEntity<String> updateRanksAfterFinalDecision() {
        try {
        	ideaRatingService.updateRanksAfterFinalDecision();
            return ResponseEntity.ok("Ranks updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update ranks: " + e.getMessage());
        }
    }
    
}
