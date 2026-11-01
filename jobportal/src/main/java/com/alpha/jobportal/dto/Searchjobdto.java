package com.alpha.jobportal.dto;

import java.time.LocalDate;
import java.util.List;

import com.alpha.jobportal.entity.Skill;

public class Searchjobdto {

    private int id;
    private String job;
    private String role;
    private List<Skill> skills;
    private Double salary;
    private int bond;
    private int reqExperiance;
    private int noOfPositions;
    private LocalDate postedDate;
    private LocalDate lastDate;
    private String reqQualification;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public int getBond() {
        return bond;
    }

    public void setBond(int bond) {
        this.bond = bond;
    }

    public int getReqExperiance() {
        return reqExperiance;
    }

    public void setReqExperiance(int reqExperiance) {
        this.reqExperiance = reqExperiance;
    }

    public int getNoOfPositions() {
        return noOfPositions;
    }

    public void setNoOfPositions(int noOfPositions) {
        this.noOfPositions = noOfPositions;
    }

    public LocalDate getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDate localDate) {
        this.postedDate = localDate;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDate localDate) {
        this.lastDate = localDate;
    }

    public String getReqQualification() {
        return reqQualification;
    }

    public void setReqQualification(String reqQualification) {
        this.reqQualification = reqQualification;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}