package com.alpha.jobportal.entity;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String role;
	@OneToMany
	private List<Skill> reqskills;
	private String jobDesc;
	private int noOfPositions;
	private Double Salary;
	private int bond;
	private int reqExperiance;
	private LocalDate postedDate;
	private LocalDate lastDate;
	private String reqQualification;
	private String status;
	@ManyToOne
	private Company company;
	@OneToMany
	private List<Application> application;
	public Job(String role, List<Skill> reqskills, String jobDesc, int noOfPositions, Double salary, int bond,
			int reqExperiance, LocalDate postedDate, LocalDate lastDate, String reqQualification, Company company,
			List<Application> application, String status) {
		super();
		this.role = role;
		this.reqskills = reqskills;
		this.jobDesc = jobDesc;
		this.noOfPositions = noOfPositions;
		Salary = salary;
		this.bond = bond;
		this.reqExperiance = reqExperiance;
		this.postedDate = postedDate;
		this.lastDate = lastDate;
		this.reqQualification = reqQualification;
		this.company = company;
		this.application = application;
		this.status=status;
	}
	public Job() {
		super();
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
		this.reqskills= reqskills;
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
		return Salary;
	}
	public void setSalary(Double salary) {
		Salary = salary;
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
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public List<Application> getApplication() {
		return application;
	}
	public void setApplication(List<Application> application) {
		this.application = application;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId() {
	    return id;
	}

	public void setId(int id) {
	    this.id = id;
	}
	
}