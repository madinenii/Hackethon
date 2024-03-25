package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.IdeaDTO;
import com.example.entity.Idea;
import com.example.service.JudgeService;

import java.util.List;

@RestController
@RequestMapping("/judges")
public class JudgeController {

    @Autowired
    private JudgeService judgeService;

//    @GetMapping("/accepted-ideas")
//    public ResponseEntity<List<Idea>> getAcceptedIdeasForJudges() {
//        List<Idea> acceptedIdeas = judgeService.getAcceptedIdeas();
//        return ResponseEntity.ok(acceptedIdeas);
//    }
    @GetMapping("/accepted-ideas")
    public ResponseEntity<List<IdeaDTO>> getAcceptedIdeas() {
        List<IdeaDTO> acceptedIdeas = judgeService.getAcceptedIdeas();
        return ResponseEntity.ok(acceptedIdeas);
    }
}