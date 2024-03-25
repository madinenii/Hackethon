package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.IdeaDTO;
import com.example.entity.Idea;
import com.example.entity.IdeaStatus;
import com.example.repo.IdeaRepository;


@Service
public class JudgeService {
	@Autowired
    private IdeaRepository ideaRepository;
	
//	public List<Idea> getAcceptedIdeas() {
//        return ideaRepository.findByStatus(IdeaStatus.ACCEPTED);
//    }
	public List<IdeaDTO> getAcceptedIdeas() {
        List<Idea> acceptedIdeas = ideaRepository.findByStatus(IdeaStatus.ACCEPTED);
        return acceptedIdeas.stream()
                            .map(this::convertToDTO)
                            .collect(Collectors.toList());
    }

    private IdeaDTO convertToDTO(Idea idea) {
        IdeaDTO ideaDTO = new IdeaDTO();
        ideaDTO.setIdeaId(idea.getIdeaId());
        ideaDTO.setIdeaTitle(idea.getIdeaTitle());
        ideaDTO.setIdeaDescription(idea.getIdeaDescription());
        ideaDTO.setIdeaDomain(idea.getIdeaDomain());
        ideaDTO.setSubmissionUrl(idea.getSubmissionUrl());
        
        return ideaDTO;
    }
}
