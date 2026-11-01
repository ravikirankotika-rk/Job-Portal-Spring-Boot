package com.alpha.jobportal.dto;

import java.time.LocalDate;
import java.util.List;

import com.alpha.jobportal.entity.Company;
import com.alpha.jobportal.entity.Skill;

public class GetalljobsDto {

	private int id;
	private String role;
	private List<Skill> reqskills;
	private String jobDesc;
	private int noOfPositions;
	private Double salary;
	private int bond;
	private int reqExperiance;
	private LocalDate postedDate;
	private LocalDate lastDate;
	private String reqQualification;
	private String status;
	private Company company;

	public GetalljobsDto() {
		super();
	}

	public GetalljobsDto(int id, String role, List<Skill> reqskills, String jobDesc, int noOfPositions, Double salary,
			int bond, int reqExperiance, LocalDate postedDate, LocalDate lastDate, String reqQualification,
			String status, Company company) {
		super();
		this.id = id;
		this.role = role;
		this.reqskills = reqskills;
		this.jobDesc = jobDesc;
		this.noOfPositions = noOfPositions;
		this.salary = salary;
		this.bond = bond;
		this.reqExperiance = reqExperiance;
		this.postedDate = postedDate;
		this.lastDate = lastDate;
		this.reqQualification = reqQualification;
		this.status = status;
		this.company = company;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Skill> getReqSkills() {
		return reqskills;
	}

	public void setReqSkills(List<Skill> reqskills) {
		this.reqskills = reqskills;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public int getNoOfPositions() {
		return noOfPositions;
	}

	public void setNoOfPositions(int noOfPositions) {
		this.noOfPositions = noOfPositions;
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

	public LocalDate getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(LocalDate postedDate) {
		this.postedDate = postedDate;
	}

	public LocalDate getLastDate() {
		return lastDate;
	}

	public void setLastDate(LocalDate lastDate) {
		this.lastDate = lastDate;
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}