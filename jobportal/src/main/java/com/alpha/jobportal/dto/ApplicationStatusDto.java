package com.alpha.jobportal.dto;

public class ApplicationStatusDto {

    private int applicationId;
    private String status;

    public ApplicationStatusDto() {
        super();
    }

    public ApplicationStatusDto(int applicationId, String status) {
        super();
        this.applicationId = applicationId;
        this.status = status;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}