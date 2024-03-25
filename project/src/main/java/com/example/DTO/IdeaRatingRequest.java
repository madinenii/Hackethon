package com.example.DTO;

public class IdeaRatingRequest {
    private int workflowMarks;
    private int explanationMarks;
    private int implementationMarks;
    private int presentationMarks;

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
}
