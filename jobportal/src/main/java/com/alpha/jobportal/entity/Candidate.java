package com.alpha.jobportal.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Candidate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	private String email;
	
	@Column(unique=true)
	private long phone;
	
	private int age;
	private String gender;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Education> education;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Experience> experience;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Skill> skill;

	@OneToMany
	private List<Application> application;

	public Candidate(String name, String email, long phone, int age, String gender, List<Education> education,
			List<Experience> experience, List<Skill> skill, List<Application> application) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.age = age;
		this.gender = gender;
		this.education = education;
		this.experience = experience;
		this.skill = skill;
		this.application = application;
	}

	public Candidate() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Education> getEducation() {
		return education;
	}

	public void setEducation(List<Education> education) {
		this.education = education;
	}

	public List<Experience> getExperience() {
		return experience;
	}

	public void setExperience(List<Experience> experience) {
		this.experience = experience;
	}

	public List<Skill> getSkill() {
		return skill;
	}

	public void setSkill(List<Skill> skill) {
		this.skill = skill;
	}

	public List<Application> getApplication() {
		return application;
	}

	public void setApplication(List<Application> application) {
		this.application = application;
	}

	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
	
	

}
