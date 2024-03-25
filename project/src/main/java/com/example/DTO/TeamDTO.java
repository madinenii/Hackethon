package com.example.DTO;

import java.util.List;

public class TeamDTO {
    private int id;
    private String teamName;
    private String ownerEmail;
    private List<TeamMemberDTO> teamMembers;

    // Constructors, getters, and setters

    public TeamDTO() {
    }

    public TeamDTO(int id, String teamName, String ownerEmail, List<TeamMemberDTO> teamMembers) {
        this.id = id;
        this.teamName = teamName;
        this.ownerEmail = ownerEmail;
        this.teamMembers = teamMembers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public List<TeamMemberDTO> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<TeamMemberDTO> teamMembers) {
        this.teamMembers = teamMembers;
    }
}
