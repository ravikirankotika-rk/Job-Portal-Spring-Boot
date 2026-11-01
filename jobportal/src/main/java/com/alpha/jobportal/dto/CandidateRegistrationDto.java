package com.alpha.jobportal.dto;

import org.springframework.boot.context.properties.bind.Name;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class CandidateRegistrationDto {
	
	
	private String name;
	
	@Email
	private String mail;
	private long phone;
	@Min(value = 1)
	@Max(value = 100)
	private int age;
	
	private String gender;
	public CandidateRegistrationDto(String name, String mail, long phone, int age, String gender) {
		super();
		this.name = name;
		this.mail = mail;
		this.phone = phone;
		this.age = age;
		this.gender = gender;
	}
	public CandidateRegistrationDto() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
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
	
	

}
