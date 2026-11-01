package com.alpha.jobportal.dto;

import java.util.List;

import com.alpha.jobportal.entity.Education;
import com.alpha.jobportal.entity.Experience;
import com.alpha.jobportal.entity.Skill;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class CandidateProfileDto {

    private String name;

    @Email
    private String mail;

    private long phone;

    @Min(value = 1)
    @Max(value = 100)
    private int age;

    private String gender;

    private List<Education> education;

    private List<Experience> experience;

    private List<Skill> skill;

    public CandidateProfileDto() {
        super();
    }

    public CandidateProfileDto(String name, String mail, long phone, int age,
            String gender, List<Education> education,
            List<Experience> experience, List<Skill> skill) {

        super();
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
        this.education = education;
        this.experience = experience;
        this.skill = skill;
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
}