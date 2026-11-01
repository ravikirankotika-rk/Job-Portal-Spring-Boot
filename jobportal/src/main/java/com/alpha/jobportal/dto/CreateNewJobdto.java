package com.alpha.jobportal.dto;

import java.time.LocalDate;
import java.util.List;

public class CreateNewJobdto {

    private int companyid;

    private List<Integer> skillids;

    private String role;

    private String jobdescription;

    private int noofpositions;

    private double salary;

    private int bond;

    private int experience;

    private LocalDate lastdate;

    private String requalification;
    
    private String status;

	public CreateNewJobdto(int companyid, List<Integer> skillids, String role, String jobdescription, int noofpositions,
			double salary, int bond, int experience, LocalDate lastdate, String requalification, String status) {
		super();
		this.companyid = companyid;
		this.skillids = skillids;
		this.role = role;
		this.jobdescription = jobdescription;
		this.noofpositions = noofpositions;
		this.salary = salary;
		this.bond = bond;
		this.experience = experience;
		this.lastdate = lastdate;
		this.requalification = requalification;
		this.status = status;
	}

	public CreateNewJobdto() {
		super();
	}

	public int getCompanyid() {
		return companyid;
	}

	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}

	public List<Integer> getSkillids() {
		return skillids;
	}

	public void setSkillids(List<Integer> skillids) {
		this.skillids = skillids;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getJobdescription() {
		return jobdescription;
	}

	public void setJobdescription(String jobdescription) {
		this.jobdescription = jobdescription;
	}

	public int getNoofpositions() {
		return noofpositions;
	}

	public void setNoofpositions(int noofpositions) {
		this.noofpositions = noofpositions;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getBond() {
		return bond;
	}

	public void setBond(int bond) {
		this.bond = bond;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public LocalDate getLastdate() {
		return lastdate;
	}

	public void setLastdate(LocalDate lastdate) {
		this.lastdate = lastdate;
	}

	public String getRequalification() {
		return requalification;
	}

	public void setRequalification(String requalification) {
		this.requalification = requalification;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
    
}