package com.example.DTO;

import com.example.entity.RanksEnum;

public class IdeaDTO {
	private int ideaId;
//    private UserDTO user;
    private String ideaTitle;
    private String ideaDescription;
    private String ideaDomain;
    private String submissionUrl;
    private RanksEnum ranking;
	public int getIdeaId() {
		return ideaId;
	}
	public void setIdeaId(int ideaId) {
		this.ideaId = ideaId;
	}
//	public UserDTO getUser() {
//		return user;
//	}
//	public void setUser(UserDTO user) {
//		this.user = user;
//	}
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
	public RanksEnum getRanking() {
		return ranking;
	}
	public void setRanking(RanksEnum ranking) {
		this.ranking = ranking;
	}
	
    
    

}
