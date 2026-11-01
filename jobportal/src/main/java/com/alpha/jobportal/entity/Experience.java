package com.alpha.jobportal.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Experience {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int id;
	private int no_ofemps;
	private String role;
	private String companyname;
	private String startdate;
	private String enddate;
	private String currently_working;
	private String description;
	
	@OneToMany
	private List<Skill> skill;

	public Experience(int no_ofemps, String role, String companyname, String startdate, String enddate,
			String currently_working, String description, List<Skill> skill) {
		super();
		this.no_ofemps = no_ofemps;
		this.role = role;
		this.companyname = companyname;
		this.startdate = startdate;
		this.enddate = enddate;
		this.currently_working = currently_working;
		this.description = description;
		this.skill = skill;
	}

	public Experience() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNo_ofemps() {
		return no_ofemps;
	}

	public void setNo_ofemps(int no_ofemps) {
		this.no_ofemps = no_ofemps;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getCurrently_working() {
		return currently_working;
	}

	public void setCurrently_working(String currently_working) {
		currently_working = currently_working;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Skill> getSkill() {
		return skill;
	}

	public void setSkill(List<Skill> skill) {
		this.skill = skill;
	}
	
	
	
	
	

}
