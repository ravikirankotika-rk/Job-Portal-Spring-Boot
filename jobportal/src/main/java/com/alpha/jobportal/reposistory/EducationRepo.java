package com.alpha.jobportal.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.jobportal.entity.Education;

public interface EducationRepo extends JpaRepository<Education, Integer> {

}
