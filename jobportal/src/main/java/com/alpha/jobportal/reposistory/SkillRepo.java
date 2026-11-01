package com.alpha.jobportal.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.jobportal.entity.Skill;

public interface SkillRepo extends JpaRepository<Skill, Integer> {

}
