package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.IdeaDTO;
import com.example.entity.Idea;
import com.example.entity.IdeaStatus;
import com.example.repo.IdeaRepository;

@Service
public class PanelistService {

    @Autowired
    private IdeaRepository ideaRepository;
    
    @Autowired
    private IdeaService ideaService;

//    public List<Idea> getAllSubmittedIdeas() {
//        return ideaRepository.findByStatus(IdeaStatus.SUBMITTED);
//    }
    public List<IdeaDTO> getAllSubmittedIdeas() {
        List<Idea> submittedIdeas = ideaRepository.findByStatus(IdeaStatus.SUBMITTED);
        List<IdeaDTO> ideaDTOs = new ArrayList();
        
        for (Idea idea : submittedIdeas) {
            IdeaDTO ideaDTO = new IdeaDTO();
            ideaDTO.setIdeaId(idea.getIdeaId());
            ideaDTO.setIdeaTitle(idea.getIdeaTitle());
            ideaDTO.setIdeaDescription(idea.getIdeaDescription());
            ideaDTO.setIdeaDomain(idea.getIdeaDomain());
            ideaDTO.setSubmissionUrl(idea.getSubmissionUrl());
            
            // You may also need to set other properties of IdeaDTO
            
            ideaDTOs.add(ideaDTO);
        }
        
        return ideaDTOs;
    }

    private IdeaDTO convertToDto(Idea idea) {
        IdeaDTO ideaDTO = new IdeaDTO();
        ideaDTO.setIdeaId(idea.getIdeaId());
        // Set other properties accordingly
        return ideaDTO;
    }
    public void acceptIdea(int ideaId) {
        ideaRepository.updateIdeaStatus(ideaId, IdeaStatus.ACCEPTED);
    }

    public void rejectIdea(int ideaId) {
        ideaRepository.updateIdeaStatus(ideaId, IdeaStatus.REJECTED);
    }
}