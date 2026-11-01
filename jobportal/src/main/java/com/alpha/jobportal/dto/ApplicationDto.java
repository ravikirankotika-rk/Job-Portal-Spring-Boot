package com.alpha.jobportal.dto;

import org.springframework.boot.context.properties.bind.Name;

import com.alpha.jobportal.entity.Candidate;

public class ApplicationDto {

    private int id;
    

    private Candidate candidate;

    private String status;

    private String application_date;

    public ApplicationDto() {
        super();
    }

    public ApplicationDto(int id, Candidate candidate, String status, String application_date) {
        super();
        this.id = id;
        this.candidate = candidate;
        this.status = status;
        this.application_date = application_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplication_date() {
        return application_date;
    }

    public void setApplication_date(String application_date) {
        this.application_date = application_date;
    }
}