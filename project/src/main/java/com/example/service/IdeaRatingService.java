package com.example.service;


import com.example.entity.Idea;
import com.example.entity.IdeaStatus;
import com.example.entity.RanksEnum;
import com.example.repo.IdeaRepository;

import jakarta.transaction.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdeaRatingService {

    private final IdeaRepository ideaRepository;

    @Autowired
    public IdeaRatingService(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

//    public void rateIdea(Long ideaId, int workflowMarks, int explanationMarks, int implementationMarks, int presentationMarks) {
//        Idea idea = ideaRepository.findById(ideaId)
//                                  .orElseThrow(() -> new RuntimeException("Idea not found with id: " + ideaId));
//
//        // Set the rating fields in the Idea entity
//        idea.setWorkflowMarks(workflowMarks);
//        idea.setExplanationMarks(explanationMarks);
//        idea.setImplementationMarks(implementationMarks);
//        idea.setPresentationMarks(presentationMarks);
//
//        // Calculate total marks and update the entity
//        int totalMarks = workflowMarks + explanationMarks + implementationMarks + presentationMarks;
//        idea.setTotalMarks(totalMarks);
//
//        // Save the updated idea entity
//        ideaRepository.save(idea);
//    }
    
//    public void rateIdea(Long ideaId, int workflowMarks, int explanationMarks, int implementationMarks, int presentationMarks) {
//        Idea idea = ideaRepository.findById(ideaId)
//                                  .orElseThrow(() -> new RuntimeException("Idea not found with id: " + ideaId));
//
//        // Set the rating fields in the Idea entity
//        idea.setWorkflowMarks(workflowMarks);
//        idea.setExplanationMarks(explanationMarks);
//        idea.setImplementationMarks(implementationMarks);
//        idea.setPresentationMarks(presentationMarks);
//
//        // Calculate total marks and update the entity
//        int totalMarks = workflowMarks + explanationMarks + implementationMarks + presentationMarks;
//        idea.setTotalMarks(totalMarks);
//
//        // Set the ranking based on total marks
//        if (totalMarks >= 35) {
//            idea.setRanking(RanksEnum.FIRST);
//        } else if (totalMarks >= 20) {
//            idea.setRanking(RanksEnum.SECOND);
//        } else {
//            idea.setRanking(RanksEnum.THIRD);
//        }
//
//        // Save the updated idea entity
//        ideaRepository.save(idea);
//    }
    public void rateIdea(Long ideaId, int workflowMarks, int explanationMarks, int implementationMarks, int presentationMarks) {
        Idea idea = ideaRepository.findById(ideaId)
                .orElseThrow(() -> new RuntimeException("Idea not found with id: " + ideaId));

        // Set the rating fields in the Idea entity
        idea.setWorkflowMarks(workflowMarks);
        idea.setExplanationMarks(explanationMarks);
        idea.setImplementationMarks(implementationMarks);
        idea.setPresentationMarks(presentationMarks);

        // Calculate total marks and update the entity
        int totalMarks = workflowMarks + explanationMarks + implementationMarks + presentationMarks;
        idea.setTotalMarks(totalMarks);

        // Save the updated idea entity
        ideaRepository.save(idea);
    }
    
//    public void calculateRanksForAcceptedIdeas() {
//        List<Idea> acceptedIdeas = ideaRepository.findByStatus(IdeaStatus.ACCEPTED_BY_PANELISTS);
//        for (Idea idea : acceptedIdeas) {
//            int totalMarks = idea.getTotalMarks();
//            if (totalMarks >= 35) {
//                idea.setRanking(RanksEnum.FIRST);
//            } else if (totalMarks >= 20) {
//                idea.setRanking(RanksEnum.SECOND);
//            } else {
//                idea.setRanking(RanksEnum.THIRD);
//            }
//            ideaRepository.save(idea);
//        }
//    }
//    public void calculateRanksForAcceptedIdeas() {
//        List<Idea> acceptedIdeas = ideaRepository.findByStatus(IdeaStatus.ACCEPTED);
//        for (Idea idea : acceptedIdeas) {
//            int totalMarks = idea.getTotalMarks();
//            if (totalMarks >= 35 && totalMarks <=40) {
//                idea.setRanking(RanksEnum.FIRST);
//            } else if (totalMarks >= 20 && totalMarks<35) {
//                idea.setRanking(RanksEnum.SECOND);
//            } else {
//                idea.setRanking(RanksEnum.THIRD);
//            }
//            ideaRepository.save(idea);
//        }
//    }

    @Transactional
    public void calculateRanksForAcceptedIdeas() {
        List<Idea> acceptedIdeas = ideaRepository.findByStatus(IdeaStatus.ACCEPTED);
        
        // Sort acceptedIdeas based on totalMarks in descending order
        Collections.sort(acceptedIdeas, Comparator.comparingInt(Idea::getTotalMarks).reversed());
        
        // Assign ranks based on the sorted order
        RanksEnum[] ranks = RanksEnum.values(); // Get all ranks
        for (int i = 0; i < acceptedIdeas.size() && i < ranks.length; i++) {
            Idea idea = acceptedIdeas.get(i);
            idea.setRanking(ranks[i]);
            ideaRepository.save(idea);
        }
    }


//    public void updateRanksAfterFinalDecision() {
//        List<Idea> allAcceptedIdeas = ideaRepository.findByStatus(IdeaStatus.ACCEPTED);
//        for (Idea idea : allAcceptedIdeas) {
//            int totalMarks = idea.getTotalMarks();
//            if (totalMarks >= 35) {
//                idea.setRanking(RanksEnum.FIRST);
//            } else if (totalMarks >= 20 && totalMarks <35) {
//                idea.setRanking(RanksEnum.SECOND);
//            } else if(totalMarks >= 15 && totalMarks < 20) {
//                idea.setRanking(RanksEnum.THIRD);
//            }
//            ideaRepository.save(idea);
//        }
//    }
    @Transactional
    public void updateRanksAfterFinalDecision() {
        List<Idea> allAcceptedIdeas = ideaRepository.findByStatus(IdeaStatus.ACCEPTED);
        
        // Sort acceptedIdeas based on totalMarks in descending order
        Collections.sort(allAcceptedIdeas, Comparator.comparingInt(Idea::getTotalMarks).reversed());
        
        // Assign ranks based on the sorted order
        RanksEnum[] ranks = RanksEnum.values(); // Get all ranks
        for (int i = 0; i < allAcceptedIdeas.size() && i < ranks.length; i++) {
            Idea idea = allAcceptedIdeas.get(i);
            idea.setRanking(ranks[i]);
            ideaRepository.save(idea);
        }
    }
}
