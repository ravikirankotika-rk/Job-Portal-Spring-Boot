package com.alpha.jobportal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Education {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String qualification;
	private String specilization;
	private double percentage;
	private int yop;
	private String university;
	public Education(String qualification, String specilization, double percentage, int yop, String university) {
		super();
		this.qualification = qualification;
		this.specilization = specilization;
		this.percentage = percentage;
		this.yop = yop;
		this.university = university;
	}
	public Education() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getSpecilization() {
		return specilization;
	}
	public void setSpecilization(String specilization) {
		this.specilization = specilization;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public int getYop() {
		return yop;
	}
	public void setYop(int yop) {
		this.yop = yop;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	
	

}
