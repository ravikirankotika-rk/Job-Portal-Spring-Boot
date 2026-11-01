package com.alpha.jobportal.reposistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.jobportal.entity.Company;
import com.alpha.jobportal.entity.Job;

public interface CompanyRepo extends JpaRepository<Company, Integer>{

	

}
