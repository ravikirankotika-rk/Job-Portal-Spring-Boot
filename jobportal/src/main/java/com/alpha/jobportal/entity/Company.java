package com.alpha.jobportal.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	@Column(unique = true)
	private long phone;
	private String address;
	private String type;
	private int noofemps; 
	private String mail;
	
	@OneToMany
	private List<Job> job;

	public Company(String name, long phone, String address, String type, int noofemps, List<Job> job) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.type = type;
		this.noofemps = noofemps;
		this.job = job;
	}

	public Company() {
		super();
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

	public int getNoofemps() {
		return noofemps;
	}

	public void setNoofemps(int noofemps) {
		this.noofemps = noofemps;
	}

	public List<Job> getJob() {
		return job;
	}

	public void setJob(List<Job> job) {
		this.job = job;
	}
	
	
	
	
	

}
