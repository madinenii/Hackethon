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
import com.example.exception.IdeaNotFoundException;
import com.example.repo.IdeaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class IdeaService {

    @Autowired
    private IdeaRepository ideaRepository;
    @Autowired
    private ModelMapper modelMapper;

    public boolean submitIdea(Idea idea) {
        try {
            idea.setStatus(IdeaStatus.SUBMITTED); // Set status to SUBMITTED
            ideaRepository.save(idea); 
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
     // Assuming you have ModelMapper configured

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
    public List<IdeaDTO> getRankedIdeas() {
        List<Idea> allRankedIdeas = ideaRepository.findAllByOrderByTotalMarksDesc();
        return allRankedIdeas.stream()
                .limit(3)
                .map(this::convertToIdeaDTO)
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