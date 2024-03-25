package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "idea")
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idea_id")
    private int ideaId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "idea_title")
    private String ideaTitle;

    @Column(name = "idea_description")
    private String ideaDescription;

    @Column(name = "idea_domain")
    private String ideaDomain;

    @Column(name = "submission_url")
    private String submissionUrl;
    
    @Column(name = "workflow_marks")
    private Integer workflowMarks;
    
    @Column(name = "explanation_marks")
    private Integer explanationMarks;
    
    @Column(name = "implementation_marks")
    private Integer implementationMarks;
    
    @Column(name = "presenation_marks")
    private Integer presentationMarks;

    @Column(name = "total_marks")
    private Integer totalMarks; 
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private IdeaStatus status;
    
    @Enumerated(EnumType.STRING)
    private RanksEnum ranking;
    
    public Idea() {
        
    }
    
	public int getIdeaId() {
		return ideaId;
	}

	public void setIdeaId(int ideaId) {
		this.ideaId = ideaId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public String getIdeaTitle() {
		return ideaTitle;
	}

	public void setIdeaTitle(String ideaTitle) {
		this.ideaTitle = ideaTitle;
	}

	public String getIdeaDescription() {
		return ideaDescription;
	}

	public void setIdeaDescription(String ideaDescription) {
		this.ideaDescription = ideaDescription;
	}

	public String getIdeaDomain() {
		return ideaDomain;
	}

	public void setIdeaDomain(String ideaDomain) {
		this.ideaDomain = ideaDomain;
	}

	public String getSubmissionUrl() {
		return submissionUrl;
	}

	public void setSubmissionUrl(String submissionUrl) {
		this.submissionUrl = submissionUrl;
	}

	public IdeaStatus getStatus() {
		return status;
	}

	public void setStatus(IdeaStatus status) {
		this.status = status;
	}

	public int getWorkflowMarks() {
		return workflowMarks;
	}

	public void setWorkflowMarks(int workflowMarks) {
		this.workflowMarks = workflowMarks;
	}

	public int getExplanationMarks() {
		return explanationMarks;
	}

	public void setExplanationMarks(int explanationMarks) {
		this.explanationMarks = explanationMarks;
	}

	public int getImplementationMarks() {
		return implementationMarks;
	}

	public void setImplementationMarks(int implementationMarks) {
		this.implementationMarks = implementationMarks;
	}

	public int getPresentationMarks() {
		return presentationMarks;
	}

	public void setPresentationMarks(int presentationMarks) {
		this.presentationMarks = presentationMarks;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}
	
	public void calculateTotalMarks() {
        this.totalMarks = workflowMarks + explanationMarks + implementationMarks + presentationMarks;
    }


	public Idea(int ideaId, Users user, Team team, String ideaTitle, String ideaDescription, String ideaDomain,
			String submissionUrl, Integer workflowMarks, Integer explanationMarks, Integer implementationMarks,
			Integer presentationMarks, Integer totalMarks, IdeaStatus status, RanksEnum ranking) {
		super();
		this.ideaId = ideaId;
		this.user = user;
		this.team = team;
		this.ideaTitle = ideaTitle;
		this.ideaDescription = ideaDescription;
		this.ideaDomain = ideaDomain;
		this.submissionUrl = submissionUrl;
		this.workflowMarks = workflowMarks;
		this.explanationMarks = explanationMarks;
		this.implementationMarks = implementationMarks;
		this.presentationMarks = presentationMarks;
		this.totalMarks = totalMarks;
		this.status = status;
		this.ranking = ranking;
	}

	public RanksEnum getRanking() {
		return ranking;
	}

	public void setRanking(RanksEnum ranking) {
		this.ranking = ranking;
	}

	public void setWorkflowMarks(Integer workflowMarks) {
		this.workflowMarks = workflowMarks;
	}

	public void setExplanationMarks(Integer explanationMarks) {
		this.explanationMarks = explanationMarks;
	}

	public void setImplementationMarks(Integer implementationMarks) {
		this.implementationMarks = implementationMarks;
	}

	public void setPresentationMarks(Integer presentationMarks) {
		this.presentationMarks = presentationMarks;
	}

	public void setTotalMarks(Integer totalMarks) {
		this.totalMarks = totalMarks;
	}
	
	
	
	

    
}
