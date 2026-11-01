package com.alpha.jobportal.dto;

import jakarta.validation.constraints.Email;

public class CompanyRegistrationDto {
	
	private String name;
	@Email
	private String mail;
	
	private long phone;
	
	private  String address;
	private String type;
	private int no_of_emps;
	public CompanyRegistrationDto(String name, String mail, long phone, String address, String type, int no_of_emps) {
		super();
		this.name = name;
		this.mail = mail;
		this.phone = phone;
		this.address = address;
		this.type = type;
		this.no_of_emps = no_of_emps;
	}
	public CompanyRegistrationDto() {
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNo_of_emps() {
		return no_of_emps;
	}
	public void setNo_of_emps(int no_of_emps) {
		this.no_of_emps = no_of_emps;
	}
	
	

}
